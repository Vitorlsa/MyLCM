package com.example.mylcm.Retrofit;

import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.Contract.SolicitacaoDTO;
import com.example.mylcm.Retrofit.Contract.SolicitacaoPendentePrestadorDTO;
import com.example.mylcm.Retrofit.Login.LoginDTO;
import com.example.mylcm.Retrofit.Login.ServerResponse;
import com.example.mylcm.Retrofit.Profile.EditDTO;
import com.example.mylcm.Retrofit.Profile.ProfileDTO;
import com.example.mylcm.Retrofit.Profile.ProfileResponse;

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

}
