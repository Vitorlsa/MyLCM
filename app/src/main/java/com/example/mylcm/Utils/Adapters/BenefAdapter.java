package com.example.mylcm.Utils.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Activities.Beneficiarios;
import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Benef.DadosBenefResponse;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.CancelContractDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.Profile.ProfileDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Utils.Classes_Adapters.Beneficiario;
import com.example.mylcm.Utils.MaskEditUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BenefAdapter extends ArrayAdapter<Beneficiario> {

    private Context sContext;
    private List<Beneficiario> benefData = new ArrayList<>();
    private Dialog benefModal, avalContrat;
    private int idBenef, sexo, idContrato;
    private double rating;
    EditText comment;
    RatingBar aval;
    String nomeBenef, dataNascimento, telefone, cep, estado, rua, bairro, numero, complemento, avalComment;
    ArrayList<Integer> condicoes = new ArrayList<>();

    public BenefAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Beneficiario> list){
        super(context, 0, list);
        sContext = context;
        benefData = list;
        avalContrat = new Dialog(sContext);
        avalContrat.setCancelable(true);
        avalContrat.setContentView(R.layout.contrato_aval);
        avalContrat.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        comment = (EditText) avalContrat.findViewById(R.id.avalComentario);
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


        final Button more = (Button) listItem.findViewById(R.id.btnMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                benefModal.setContentView(R.layout.modal_benef);
                benefModal.setCancelable(false);
                benefModal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                idBenef = presenteBeneficiario.getBenefId();
                retrofitBenefData(idBenef);


                Button close = (Button) benefModal.findViewById(R.id.BenefClose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        benefModal.dismiss();
                        more.setEnabled(true);
                    }
                });

                more.setEnabled(false);
            }
        });

        ImageButton cancel = (ImageButton) listItem.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final AlertDialog.Builder builder = new AlertDialog.Builder(sContext, R.style.AlertDialogTheme);
               builder.setTitle("Tem certeza que gostaria de cancelar o contrato?");
               builder.setMessage("O cancelamento de contrato acarretará em total desvinculamento com o Beneficiário em questão. Gostaria mesmo de continuar?");
               builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       idContrato = presenteBeneficiario.getContratoId();

                       aval = (RatingBar) avalContrat.findViewById(R.id.rate);
                       aval.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                           @Override
                           public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                           }
                       });
                       avalContrat.findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               avalContrat.dismiss();
                               rating = aval.getRating();
                               avalComment = comment.getText().toString();
                               retrofitCancelarContrato(idContrato, avalComment, rating);
                           }
                       });

                       avalContrat.show();
                   }
               });
               builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                   }
               });

               final AlertDialog dialog = builder.create();

               dialog.show();
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
                            nomeBenef = benefResponseData.getNome();
                            dataNascimento = benefResponseData.getDataNascimento();
                            sexo = benefResponseData.getSexo();
                            telefone = benefResponseData.getTelefone();
                            cep = benefResponseData.getCep();
                            estado = benefResponseData.getEstado();
                            int cidade = benefResponseData.getCidade();
                            retrofitCidade(cidade);
                            bairro = benefResponseData.getBairro();
                            rua = benefResponseData.getRua();
                            numero = benefResponseData.getNumero();
                            complemento = benefResponseData.getComplemento();
                            condicoes = benefResponseData.getCondicoesClinicas();


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

    public void popularModalBenef(String nome, String data, int sexo, String telefone, String cep, String estado, String cityModal, String bairro, String rua, String numero, String complemento, ArrayList<Integer> condicoes){

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
        city.setText(cityModal);

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
        benefModal.show();
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
                            String city = cityResponseData;

                            popularModalBenef(nomeBenef, dataNascimento, sexo, telefone, cep, estado, city, bairro, rua, numero, complemento, condicoes);
                            //popularCidadeModal(city);

                        } else{

                            Toast.makeText(sContext.getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(sContext.getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(sContext.getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(sContext.getApplicationContext(),"Salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void popularCidadeModal(String cityModal){
        TextView city = (TextView) benefModal.findViewById(R.id.BenefCity);
        city.setText(cityModal);
    }

    public void retrofitCancelarContrato(int idContrato, String Comentario, double Rating){

        RetrofitService service = Connect.createService(RetrofitService.class);

        final CancelContractDTO cancel = new CancelContractDTO(idContrato, Comentario, Rating);

        Call<CancelContractDTO> call = service.setContractCancel(cancel);

        call.enqueue(new Callback<CancelContractDTO>() {
            @Override
            public void onResponse(Call<CancelContractDTO> call, Response<CancelContractDTO> response) {
                if (response.isSuccessful()){
                    //Toast.makeText(getActivity().getApplicationContext(),"Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(sContext.getApplicationContext(),"Obrigado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
