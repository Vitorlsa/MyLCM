package com.example.mylcm.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Retrofit.Contract.SolicitacaoDTO;
import com.example.mylcm.Retrofit.Contract.SolicitacaoPendentePrestadorDTO;
import com.example.mylcm.Utils.Solicitacao;
import com.example.mylcm.Utils.SolicitacaoAdapter;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contracts extends AppCompatActivity {

    ImageButton backBtn;
    Button accept, deny;
    TextView title, nome;
    ListView contractList;
    SwipeRefreshLayout pullRefresh;
    Dialog contractModal;
    private SolicitacaoAdapter solicitacao;
    public static int pid, qtd, idSol, idContract, idBenef, remover;
    public static String NameBenef, NameContract, ReqDate;
    public boolean yorn;
    ArrayList<Solicitacao> contractData = new ArrayList<>();

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
        contractList = (ListView) findViewById(R.id.listContract);
        pullRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefreshContracts);

        contractModal = new Dialog(this);

        pullRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(solicitacao != null){
                    solicitacao.clear();
                }
                retrofitContract(pid);
                pullRefresh.setRefreshing(false);
            }
        });

        SharedPreferences  presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        retrofitContract(pid);

        contractList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> convertView, View parent, int position, long id) {
                contractModal.setContentView(R.layout.modal_contract);
                EditText nameCon = (EditText) contractModal.findViewById(R.id.contract_name);
                nameCon.setText(contractData.get(position).getNomeContratante());
                EditText nameBen = (EditText) contractModal.findViewById(R.id.contract_benef);
                nameBen.setText(contractData.get(position).getNomeBeneficiario());
                Button close = (Button) contractModal.findViewById(R.id.btnClose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contractModal.dismiss();
                    }
                });
                contractModal.show();
            }
        });
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
                                idSol = contractResponseData.get(i).Id;
                                idContract = contractResponseData.get(i).ContratanteId;
                                idBenef = contractResponseData.get(i).BeneficiarioId;
                                ReqDate = contractResponseData.get(i).DataSolicitacao;

                                popularListaContrato();

                                popularModalContrato();

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

        contractData.add(new Solicitacao(idSol, idBenef, idContract, NameBenef, NameContract, ReqDate));

        solicitacao = new SolicitacaoAdapter(this, contractData);
        contractList.setAdapter(solicitacao);

    }

    public void popularModalContrato(){

        //contractData.add(new Solicitacao(idSol, idBenef, idContract, NameBenef, NameContract, ReqDate));


    }

    public void retrofitAceitar(int id, boolean aceitou){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final SolicitacaoDTO aceitar = new SolicitacaoDTO(id, aceitou);

        Call<SolicitacaoDTO> call = service.setSolicitacao(aceitar);

        call.enqueue(new Callback<SolicitacaoDTO>() {
            @Override
            public void onResponse(Call<SolicitacaoDTO> call, Response<SolicitacaoDTO> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<SolicitacaoDTO> call, Throwable t) {

            }
        });
    }
}
