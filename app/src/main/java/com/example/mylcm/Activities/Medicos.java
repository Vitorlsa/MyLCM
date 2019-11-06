package com.example.mylcm.Activities;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.Medicos.MedicosBenefDTO;
import com.example.mylcm.Retrofit.Profile.ProfileDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Utils.Adapters.MedicAdapter;
import com.example.mylcm.Utils.Classes_Adapters.MedicosBenef;
import com.example.mylcm.Utils.StringWithTag;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medicos extends AppCompatActivity {

    ImageButton backBtn;
    Spinner spnBenefs;
    ListView listMedic;
    Dialog modalMedics;
    public static int pid, qtd, benefId, TelMedico, CelMedico, cityID;
    public String NameBenef, NomeMedico, CepMedico, BairroMedico, RuaMedico, NumeroMedico, EstadoMedico, ComplementoMedico, EspecialidadeMedico, Convenio, CidadeMedico;
    public boolean conv;
    ArrayList<StringWithTag> benefNames = new ArrayList<>();
    ArrayList<MedicosBenef> medicData = new ArrayList<>();
    private MedicAdapter medicAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);

        backBtn = (ImageButton) findViewById(R.id.imgbtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spnBenefs = (Spinner) findViewById(R.id.spnBenef);
        listMedic = (ListView) findViewById(R.id.medicList);

        modalMedics = new Dialog(this);

        SharedPreferences presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        benefNames.add(new StringWithTag("Selecione um Beneficiário", 0));
        ArrayAdapter<StringWithTag> benefNamesAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, benefNames);
        spnBenefs.setAdapter(benefNamesAdapter);

        retrofitBenefNames(pid);

        spnBenefs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                StringWithTag s = (StringWithTag) adapterView.getItemAtPosition(pos);
                Object tag = s.tag;
                int idBenef = Integer.parseInt(tag.toString());

                if(idBenef == 0){
                    if(medicAdapter != null){
                        medicData.clear();
                        medicAdapter.notifyDataSetChanged();
                        listMedic.setAdapter(medicAdapter);
                    }
                }

                if(idBenef != 0){

                    medicData.clear();
                    retrofitMedicos(idBenef);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listMedic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                modalMedics.setContentView(R.layout.modal_medicos);
                modalMedics.setCancelable(false);
                modalMedics.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView NomeMedic = (TextView) modalMedics.findViewById(R.id.MedicName);
                NomeMedic.setText(NomeMedico);
                TextView SpecialMedic = (TextView) modalMedics.findViewById(R.id.MedicSpecialty);
                SpecialMedic.setText(EspecialidadeMedico);
                TextView TelMedic = (TextView) modalMedics.findViewById(R.id.MedicPraxNumber);
                TelMedic.setText(String.valueOf(TelMedico));
                TextView CelMedic = (TextView) modalMedics.findViewById(R.id.MedicCelNumber);
                CelMedic.setText(String.valueOf(CelMedico));
                TextView ConvMedic = (TextView) modalMedics.findViewById(R.id.MedicConvenio);
                ConvMedic.setText(Convenio);
                TextView MedicZip = (TextView) modalMedics.findViewById(R.id.MedicZip);
                MedicZip.setText(CepMedico);
                TextView MedicState = (TextView) modalMedics.findViewById(R.id.MedicState);
                MedicState.setText(EstadoMedico);
                TextView MedicCity = (TextView) modalMedics.findViewById(R.id.MedicCity);
                MedicCity.setText(CidadeMedico);
                TextView MedicNHood = (TextView) modalMedics.findViewById(R.id.MedicNHood);
                MedicNHood.setText(BairroMedico);
                TextView StreetMedic = (TextView) modalMedics.findViewById(R.id.MedicStreet);
                StreetMedic.setText(RuaMedico);
                TextView MedicNumber = (TextView) modalMedics.findViewById(R.id.MedicNumber);
                MedicNumber.setText(NumeroMedico);
                TextView ComplementMedic = (TextView) modalMedics.findViewById(R.id.MedicComplement);
                ComplementMedic.setText(ComplementoMedico);

                Button close = (Button) modalMedics.findViewById(R.id.MedicClose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        modalMedics.dismiss();
                    }
                });

                modalMedics.show();
            }
        });
    }

    public void retrofitBenefNames(int pid){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO benef = new ContractDTO(pid);

        Call<ArrayList<BenefDTO>> call = service.getBenef(benef);

        call.enqueue(new Callback<ArrayList<BenefDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<BenefDTO>> call, Response<ArrayList<BenefDTO>> response) {

                if (response.isSuccessful()) {

                    ArrayList<BenefDTO> benefResponse = response.body();
                    qtd = response.body().size();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (benefResponse != null) {

                        for(int i = 0; i < qtd; i++){

                            if(benefResponse.get(i).Id != 0) {

                                ArrayList<BenefDTO> benefResponseData = response.body();
                                NameBenef = benefResponseData.get(i).NomeBeneficiario;
                                benefId = benefResponseData.get(i).BeneficiarioId;

                                popularSpinnerBenef();

                            } else{

                                Toast.makeText(getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();

                }

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<BenefDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrofitMedicos(int id) {
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO medic = new ContractDTO(id);

        Call<ArrayList<MedicosBenefDTO>> call = service.getMedicos(medic);

        call.enqueue(new Callback<ArrayList<MedicosBenefDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<MedicosBenefDTO>> call, Response<ArrayList<MedicosBenefDTO>> response) {

                if (response.isSuccessful()) {

                    ArrayList<MedicosBenefDTO> medicResponse = response.body();
                    qtd = response.body().size();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (medicResponse != null) {

                        for (int i = 0; i < qtd; i++) {

                            if (medicResponse.get(i).Id != 0) {

                                ArrayList<MedicosBenefDTO> medicResponseData = response.body();
                                NomeMedico = medicResponseData.get(i).Nome;
                                TelMedico = medicResponseData.get(i).TelefoneConsultorio;
                                CelMedico = medicResponseData.get(i).Celular;
                                cityID = medicResponseData.get(i).CidadeId;
                                CepMedico = medicResponseData.get(i).Cep;
                                BairroMedico = medicResponseData.get(i).Bairro;
                                RuaMedico = medicResponseData.get(i).Rua;
                                NumeroMedico = medicResponseData.get(i).Numero;
                                EstadoMedico = medicResponseData.get(i).EstadoUf;
                                ComplementoMedico = medicResponseData.get(i).Complemento;
                                EspecialidadeMedico = medicResponseData.get(i).EspecialidadeNome;
                                conv = medicResponseData.get(i).Convenio;

                                if(conv == true){
                                    Convenio = "Sim";
                                }
                                else {
                                    Convenio = "Não";
                                }

                                retrofitCidade(cityID);
                                popularListaMedic();

                            } else {

                                Toast.makeText(getApplicationContext(), "Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {

                        Toast.makeText(getApplicationContext(), "Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();

                }

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<MedicosBenefDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void popularSpinnerBenef(){
        benefNames.add(new StringWithTag(NameBenef, benefId));
        ArrayAdapter<StringWithTag> benefNamesAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, benefNames);
        spnBenefs.setAdapter(benefNamesAdapter);
    }

    public void popularListaMedic(){
        medicData.add(new MedicosBenef(NomeMedico, TelMedico));
        medicAdapter = new MedicAdapter(this, medicData);
        listMedic.setAdapter(medicAdapter);
    }

    public void retrofitCidade(int cidadeId){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ProfileDTO city = new ProfileDTO(cidadeId);

        Call<String> call = service.getCityName(city);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Response", response.body().toString());
                if (response.isSuccessful()) {

                    String cityResponse = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (cityResponse != null) {

                        if(cityResponse != null) {

                            String cityResponseData = response.body();
                            CidadeMedico = cityResponseData;

                        } else{

                            Toast.makeText(getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
