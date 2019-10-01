package com.example.mylcm.Activities;

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
import com.example.mylcm.Retrofit.Benef.BenefMedDTO;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Utils.Adapters.MedAdapter;
import com.example.mylcm.Utils.Classes_Adapters.Medicamento;
import com.example.mylcm.Utils.StringWithTag;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medicamentos extends AppCompatActivity {

    ImageButton backBtn;
    Spinner spnBenefs;
    ListView listMed;
    public static int qtd, pid, benefId, qtdMed;
    public static String NameBenef, NameMed, Dosage;
    ArrayList<StringWithTag> benefNames = new ArrayList<>();
    ArrayList<Medicamento> medData = new ArrayList<>();
    private MedAdapter medAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);

        backBtn = (ImageButton) findViewById(R.id.imgbtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spnBenefs = (Spinner) findViewById(R.id.spnBenef);
        listMed = (ListView) findViewById(R.id.medList);

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
                    if(medAdapter != null){
                        medData.clear();
                        medAdapter.notifyDataSetChanged();
                        listMed.setAdapter(medAdapter);
                    }
                }

                if(idBenef != 0){

                    medData.clear();
                    retrofitMedicamentos(idBenef);

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

    public void retrofitMedicamentos(int id){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO med = new ContractDTO(id);

        Call<ArrayList<BenefMedDTO>> call = service.getMedicamento(med);

        call.enqueue(new Callback<ArrayList<BenefMedDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<BenefMedDTO>> call, Response<ArrayList<BenefMedDTO>> response) {

                if (response.isSuccessful()) {

                    ArrayList<BenefMedDTO> medResponse = response.body();
                    qtd = response.body().size();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (medResponse != null) {

                        for(int i = 0; i < qtd; i++){

                            if(medResponse.get(i).Id != 0) {

                                ArrayList<BenefMedDTO> medResponseData = response.body();
                                NameMed = medResponseData.get(i).NomeMedicamento;
                                Dosage = medResponseData.get(i).Posologia;
                                qtdMed = medResponseData.get(i).Quantidade;

                                popularListaMed();

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
            public void onFailure(Call<ArrayList<BenefMedDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void popularSpinnerBenef(){
        benefNames.add(new StringWithTag(NameBenef, benefId));
        ArrayAdapter<StringWithTag> benefNamesAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, benefNames);
        spnBenefs.setAdapter(benefNamesAdapter);
    }

    public void popularListaMed(){
        medData.add(new Medicamento(NameMed, Dosage, qtdMed));
        medAdapter = new MedAdapter(this, medData);
        listMed.setAdapter(medAdapter);
    }
}
