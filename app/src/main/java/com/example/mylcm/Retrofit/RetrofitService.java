package com.example.mylcm.Retrofit;

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

}
