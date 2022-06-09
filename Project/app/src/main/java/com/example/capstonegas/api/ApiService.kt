package com.example.capstonegas.api

import com.example.capstonegas.model.AllIngredientResponse
import com.example.capstonegas.model.LoginResponse
import com.example.capstonegas.model.RegisterResponse
import com.example.capstonegas.model.SearchIngredientResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("/User/userRegister")
    fun registerUsers(
        @Field("FullName") name: String,
        @Field("Username") username: String,
        @Field("Email") email: String,
        @Field("Password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("/Login/userLogin")
    fun loginUser(
        @Field("Username") username: String,
        @Field("Password") password: String
    ): Call<LoginResponse>

    // Search Ingredients
    @FormUrlEncoded
    @POST("/Ingredient/getIngredByName")
    fun getIngredient(
        @Header("Authorization") token: String,
        @Field("IngredName") query: String
    ): Call<SearchIngredientResponse>

    @GET("/Ingredient/getAllIngredient")
    fun getAllIngredient(
        @Header("Authorization") token: String
    ): Call<AllIngredientResponse>
}