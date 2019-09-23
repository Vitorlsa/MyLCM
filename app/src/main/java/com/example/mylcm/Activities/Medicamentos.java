package com.example.mylcm.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Medicamentos extends AppCompatActivity {

    ImageButton backBtn;
    Spinner spnBenefs;
    public static int qtd, pid;
    public static String NameBenef;
    ArrayList<String> benefNames = new ArrayList<String>();

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

        SharedPreferences presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        benefNames.add("Selecione um Beneficiário");
        ArrayAdapter<String> benefNamesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, benefNames);
        spnBenefs.setAdapter(benefNamesAdapter);

        retrofitBenefNames(pid);
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

    public void popularSpinnerBenef(){
        benefNames.add(NameBenef);
        ArrayAdapter<String> benefNamesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, benefNames);
        spnBenefs.setAdapter(benefNamesAdapter);
    }
}
