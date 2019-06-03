package com.example.mylcm.Retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    //@Headers("X-Mashape-Key: AuuyclCPjcmshv2iOPq190OpzLrMp1FJWwejsnJrdfwOUr4h44")

    @POST("api/usuario/logar")
    Call<ServerResponse> getCredentials(@Body LoginDTO login);

}
