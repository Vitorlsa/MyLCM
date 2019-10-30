package com.example.mylcm.Activities;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.Medicos.MedicosBenefDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Utils.Adapters.MedicAdapter;
import com.example.mylcm.Utils.Classes_Adapters.MedicosBenef;
import com.example.mylcm.Utils.StringWithTag;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medicos extends AppCompatActivity {

    ImageButton backBtn;
    Spinner spnBenefs;
    ListView listMedic;
    Dialog modalMedics;
    public static int pid, qtd, benefId, TelMedico;
    public String NameBenef,NomeMedico;
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
}
