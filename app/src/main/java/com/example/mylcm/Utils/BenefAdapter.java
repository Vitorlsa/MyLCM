package com.example.mylcm.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Activities.Beneficiarios;
import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Benef.DadosBenefResponse;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BenefAdapter extends ArrayAdapter<Beneficiario> {

    private Context sContext;
    private List<Beneficiario> benefData = new ArrayList<>();
    private Dialog benefModal;
    private int idBenef;

    public BenefAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Beneficiario> list){
        super(context, 0, list);
        sContext = context;
        benefData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.item_benef, parent,false);


        final Beneficiario presenteBeneficiario = benefData.get(position);

        benefModal = new Dialog(sContext);

        TextView nameContract = (TextView) listItem.findViewById(R.id.contract_name);
        nameContract.setText(presenteBeneficiario.getNomeContratante());

        TextView nameBenef = (TextView) listItem.findViewById(R.id.contract_benef);
        nameBenef.setText(presenteBeneficiario.getNomeBeneficiario());


        Button more = (Button) listItem.findViewById(R.id.btnMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                benefModal.setContentView(R.layout.modal_benef);
                benefModal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                idBenef = presenteBeneficiario.getBenefId();
                retrofitBenefData(idBenef);


                Button close = (Button) benefModal.findViewById(R.id.BenefClose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        benefModal.dismiss();
                    }
                });

                benefModal.show();
            }
        });
        return listItem;
    }

    public void retrofitBenefData(int idBenef){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO benefData = new ContractDTO(idBenef);

        Call<DadosBenefResponse> call = service.getDataBenef(benefData);

        call.enqueue(new Callback<DadosBenefResponse>() {
            @Override
            public void onResponse(Call<DadosBenefResponse> call, Response<DadosBenefResponse> response) {

                if (response.isSuccessful()) {

                    DadosBenefResponse benefResponse = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (benefResponse != null) {

                        if(benefResponse.getId() != 0) {

                            DadosBenefResponse benefResponseData = response.body();
                            String nomeBenef = benefResponseData.getNome();
                            String dataNascimento = benefResponseData.getDataNascimento();
                            int sexo = benefResponseData.getSexo();
                            String telefone = benefResponseData.getTelefone();
                            String cep = benefResponseData.getCep();
                            String estado = benefResponseData.getEstado();
                            int cidade = benefResponseData.getCidade();
                            String bairro = benefResponseData.getBairro();
                            String rua = benefResponseData.getRua();
                            String numero = benefResponseData.getNumero();
                            String complemento = benefResponseData.getComplemento();
                            ArrayList<Integer> condicoes = benefResponseData.getCondicoesClinicas();

                            popularModalBenef(nomeBenef, dataNascimento, sexo, telefone, cep, estado, cidade, bairro, rua, numero, complemento, condicoes);

                        } else{

                            Toast.makeText(sContext.getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(sContext.getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(sContext.getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();

                }

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<DadosBenefResponse> call, Throwable t) {
                Toast.makeText(sContext.getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void popularModalBenef(String nome, String data, int sexo, String telefone, String cep, String estado, int cidade, String bairro, String rua, String numero, String complemento, ArrayList<Integer> condicoes){

        TextView name = (TextView) benefModal.findViewById(R.id.BenefName);
        name.setText(nome);

        data = data.substring(0, 10);
        String dataN[] = data.split("-");
        TextView date = (TextView) benefModal.findViewById(R.id.BenefBirth);
        date.setText(dataN[2] + "/" + dataN[1] + "/" + dataN[0]);

        TextView sex = (TextView) benefModal.findViewById(R.id.BenefSex);
        if(sexo == 1){
            sex.setText("Masculino");
        }else if (sexo == 2){
            sex.setText("Feminino");
        }else {
            sex.setText("Outros");
        }

        TextView tel = (TextView) benefModal.findViewById(R.id.BenefTel);
        tel.setText(telefone);

        EditText zip = (EditText) benefModal.findViewById(R.id.BenefZip);
        zip.addTextChangedListener(MaskEditUtil.mask(zip, MaskEditUtil.FORMAT_CEP));
        zip.setText(cep);

        TextView state = (TextView) benefModal.findViewById(R.id.BenefState);
        state.setText(estado);

        TextView city = (TextView) benefModal.findViewById(R.id.BenefCity);
        city.setText(Integer.toString(cidade));

        TextView nhood = (TextView) benefModal.findViewById(R.id.BenefNHood);
        nhood.setText(bairro);

        TextView street = (TextView) benefModal.findViewById(R.id.BenefStreet);
        street.setText(rua);

        TextView number = (TextView) benefModal.findViewById(R.id.BenefNumber);
        number.setText(numero);

        TextView complement = (TextView) benefModal.findViewById(R.id.BenefComplement);
        complement.setText(complemento);

        Spinner condition = (Spinner) benefModal.findViewById(R.id.SpnBenefCondition);
        List<String> spnConditionArray = new ArrayList<String>();
        ArrayAdapter contidionAdapter = new ArrayAdapter(sContext, R.layout.spinner_condition_item, spnConditionArray);
        int qtd;
        qtd = condicoes.size();
        for (int i = 0; i < qtd; i++){
            if(condicoes.get(i).intValue() == 1){
                spnConditionArray.add("Acamado");
            }

            if(condicoes.get(i).intValue() == 2){
                spnConditionArray.add("Em coma");
            }

            if(condicoes.get(i).intValue() == 3){
                spnConditionArray.add("Possui drenos");
            }

            if(condicoes.get(i).intValue() == 4){
                spnConditionArray.add("Se locomove");
            }

            if(condicoes.get(i).intValue() == 5){
                spnConditionArray.add("Consegue comer");
            }
        }
        condition.setAdapter(contidionAdapter);

    }
}
