package com.example.mylcm.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {

    @Headers("X-Mashape-Key: AuuyclCPjcmshv2iOPq190OpzLrMp1FJWwejsnJrdfwOUr4h44")

    @FormUrlEncoded
    @POST("convert")
    Call<ServerResponse> getCredentials(@Field("from-type") String usuario,
                                    @Field("from-value") String password,
                                    @Field("to-type") String to_type);

}
