package com.example.mylcm.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.ContractDTO;
import com.example.mylcm.Retrofit.ContractResponse;
import com.example.mylcm.Retrofit.RetrofitService;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contracts extends AppCompatActivity {

    ImageButton backBtn;
    public static int pid;
    public static String NameBenef, NameContract, ReqDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracts);

        backBtn = (ImageButton) findViewById(R.id.imgbtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences  presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        retrofitContract(pid);
     }

    public void retrofitContract(int pid){

        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO contract = new ContractDTO(pid);

        Call<ContractResponse> call = service.getContracts(contract);

        call.enqueue(new Callback<ContractResponse>() {
            @Override
            public void onResponse(Call<ContractResponse> call, Response<ContractResponse> response) {

                if (response.isSuccessful()) {

                    ContractResponse contractResponse = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (contractResponse != null) {

                        if(!contractResponse.getContract().equals(0)) {

                            ContractResponse contractResponseData = response.body();


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

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<ContractResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
