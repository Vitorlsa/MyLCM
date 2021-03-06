package com.example.mylcm.Retrofit;

import com.example.mylcm.Retrofit.Benef.BenefMedDTO;
import com.example.mylcm.Retrofit.Benef.DadosBenefResponse;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.CancelContractDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.Contract.DadosContratosResponse;
import com.example.mylcm.Retrofit.Contract.SolicitacaoDTO;
import com.example.mylcm.Retrofit.Contract.SolicitacaoPendentePrestadorDTO;
import com.example.mylcm.Retrofit.Login.LoginDTO;
import com.example.mylcm.Retrofit.Login.ServerResponse;
import com.example.mylcm.Retrofit.Medicos.MedicosBenefDTO;
import com.example.mylcm.Retrofit.Profile.EditDTO;
import com.example.mylcm.Retrofit.Profile.ProfileDTO;
import com.example.mylcm.Retrofit.Profile.ProfileResponse;
import com.example.mylcm.Retrofit.Tasks.TasksDTO;
import com.example.mylcm.Retrofit.Tasks.TasksDoneDTO;
import com.example.mylcm.Retrofit.Tasks.TasksResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    //@Headers("X-Mashape-Key: AuuyclCPjcmshv2iOPq190OpzLrMp1FJWwejsnJrdfwOUr4h44")

    @POST("api/usuario/logarprestador")
    Call<ServerResponse> getCredentials(@Body LoginDTO login);

    @POST("api/prestadordeservico/buscar")
    Call<ProfileResponse> getData(@Body ProfileDTO id);

    @POST("api/prestadordeservico/editar")
    Call<EditDTO> setEdit(@Body EditDTO prestador);

    @POST("api/prestadordeservico/listarsolicitacoesdecontrato")
    Call<ArrayList<SolicitacaoPendentePrestadorDTO>> getContracts(@Body ContractDTO Id);

    @POST("api/prestadordeservico/aceitarounaosolicitacao")
    Call<SolicitacaoDTO> setSolicitacao(@Body SolicitacaoDTO solicitacao);

    @POST("api/contrato/listarcontratosvigentesprestador")
    Call<ArrayList<BenefDTO>> getBenef(@Body ContractDTO Id);

    @POST("api/contratante/buscardadossolicitacaocontratanteprestador")
    Call<DadosContratosResponse> getDataContract(@Body ContractDTO idSol);

    @POST("api/beneficiario/buscarporid")
    Call<DadosBenefResponse> getDataBenef(@Body ContractDTO idBenef);

    @POST("api/usuario/nomecidade")
    Call<String> getCityName(@Body ProfileDTO idCidade);

    @POST("api/beneficiario/listarmedicamento")
    Call<ArrayList<BenefMedDTO>> getMedicamento(@Body ContractDTO idBenef);

    @POST("api/tarefa/listartodastarefasprestadorpordia")
    Call<ArrayList<TasksResponse>> getTasks(@Body TasksDTO tasks);

    @POST("api/tarefa/realizartarefa")
    Call<TasksDoneDTO> setTasksDone(@Body TasksDoneDTO tasksDone);

    @POST("api/beneficiario/listarmedico")
    Call<ArrayList<MedicosBenefDTO>> getMedicos(@Body ContractDTO idBenef);

    @POST("api/contrato/encerrarcontratoprestador")
    Call<CancelContractDTO> setContractCancel(@Body CancelContractDTO idContrato);
}
