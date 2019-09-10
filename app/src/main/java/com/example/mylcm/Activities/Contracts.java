package com.example.mylcm.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Retrofit.SolicitacaoPendentePrestadorDTO;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contracts extends AppCompatActivity {

    ImageButton backBtn;
    TextView title, nome;
    ListView contractList;
    private ArrayAdapter<String> contrato;
    public static int pid, qtd;
    public static String NameBenef, NameContract, ReqDate;
    ArrayList<String> contractData = new ArrayList<>();

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
        title = (TextView) findViewById(R.id.txtTitle);
        contractList = (ListView) findViewById(R.id.listContract);

        SharedPreferences  presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        retrofitContract(pid);

     }

    public void retrofitContract(int pid){

        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO contract = new ContractDTO(pid);

        Call<ArrayList<SolicitacaoPendentePrestadorDTO>> call = service.getContracts(contract);

        call.enqueue(new Callback<ArrayList<SolicitacaoPendentePrestadorDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<SolicitacaoPendentePrestadorDTO>> call, Response<ArrayList<SolicitacaoPendentePrestadorDTO>> response) {

                if (response.isSuccessful()) {

                    ArrayList<SolicitacaoPendentePrestadorDTO> contractResponse = response.body();
                    qtd = response.body().size();

                    ArrayAdapter<String> contrato;


                    //verifica aqui se o corpo da resposta não é nulo
                    if (contractResponse != null) {

                        for(int i = 0; i < qtd; i++){

                            if(contractResponse.get(i).Id != 0) {

                                ArrayList<SolicitacaoPendentePrestadorDTO> contractResponseData = response.body();
                                NameBenef = contractResponseData.get(i).NomeBeneficiario;
                                NameContract = contractResponseData.get(i).NomeContratante;

                                popularListaContrato();

                            } else{

                                Toast.makeText(getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                            }
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
            public void onFailure(Call<ArrayList<SolicitacaoPendentePrestadorDTO>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void popularListaContrato(){

        contractData.add(NameContract + "\n" + NameBenef);

        contrato = new ArrayAdapter<>(this, R.layout.item_contract, R.id.contract_name, contractData);
        contractList.setAdapter(contrato);

    }

    public void aceitarContrato(boolean aceitar){
        aceitar = true;
    }

    public void negarContrato(boolean negar){
        negar = false;
    }
}
