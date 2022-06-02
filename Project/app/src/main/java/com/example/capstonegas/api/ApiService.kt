package com.example.capstonegas.api

import com.example.capstonegas.model.LoginResponse
import com.example.capstonegas.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/User/userRegister")
    fun registerUsers(
        @Field("Name") name: String,
        @Field("Username") username: String,
        @Field("Email") email: String,
        @Field("Password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("/User/userLogin")
    fun loginUser(
        @Field("Username") username: String,
        @Field("Password") password: String
    ): Call<LoginResponse>
}