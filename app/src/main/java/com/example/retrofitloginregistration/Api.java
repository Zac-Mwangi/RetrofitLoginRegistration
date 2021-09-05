package com.example.retrofitloginregistration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @POST("register.php")
    @FormUrlEncoded
    Call<Model> register(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @POST("login.php")
    @FormUrlEncoded
    Call<Model> login(@Field("username") String username, @Field("password") String password);
}