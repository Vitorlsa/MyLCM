package com.example.mylcm.Activities;

import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Utils.Adapters.BenefAdapter;
import com.example.mylcm.Utils.Classes_Adapters.Beneficiario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Beneficiarios extends AppCompatActivity {

    ImageButton backBtn;
    ListView listBenef;
    public static  int pid, qtd, benefId, idContrato;
    public static String NameBenef, NameContract;
    public static String IniDate, EndDate;
    ArrayList<Beneficiario> benefData = new ArrayList<>();
    private BenefAdapter benefs;
    SwipeRefreshLayout pullRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiario);

        backBtn = (ImageButton) findViewById(R.id.imgbtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pullRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefreshBenefs);
        listBenef = (ListView) findViewById(R.id.listBenef);

        SharedPreferences presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        pullRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(benefs != null){
                    benefs.clear();
                }
                retrofitBenef(pid);
                pullRefresh.setRefreshing(false);
            }
        });

        retrofitBenef(pid);
    }

    public void retrofitBenef(int pid){
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
                                NameContract = benefResponseData.get(i).NomeContratante;
                                IniDate = benefResponseData.get(i).DataInicio;
                                EndDate = benefResponseData.get(i).DataFim;
                                benefId = benefResponseData.get(i).BeneficiarioId;
                                idContrato = benefResponseData.get(i).Id;

                                popularListaBenef();

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

    public void popularListaBenef(){

        benefData.add(new Beneficiario(NameBenef, NameContract, benefId, idContrato));
        benefs = new BenefAdapter(this, benefData);
        listBenef.setAdapter(benefs);

    }
}
