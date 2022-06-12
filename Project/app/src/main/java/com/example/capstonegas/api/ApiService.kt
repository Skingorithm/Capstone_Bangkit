package com.example.capstonegas.api

import com.example.capstonegas.model.*
import okhttp3.MultipartBody
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

    // Get All Ingredients
    @GET("/Ingredient/getAllIngredient")
    fun getAllIngredient(
        @Header("Authorization") token: String
    ): Call<AllIngredientResponse>

    //Add Alarm
    @FormUrlEncoded
    @POST("/Alarm/insertAlarm")
    fun insertAlarm(
        @Header("Authorization") token: String,
        @Field("RoutinityName") routinityName: String,
        @Field("NotifyHour") notifyHour: String,
        @Field("AlarmDate") alarmDate: String,
        @Field("fifteenBefore") fifteenBefore: Boolean,
        @Field("thirtyBefore") thirtyBefore: Boolean,
        @Field("repeatAlarm") repeatAlarm: Int,
        @Field("Username") username: String
    ): Call<InsertAlarmResponse>

    @Multipart
    @POST("")
    fun uploadImage(
        @Header("Authorization") token : String,
        @Part file: MultipartBody.Part
    ): Call<Any>

    // Get User by Username
    @FormUrlEncoded
    @POST("/User/getUserByUsername")
    fun getUserByUsername(
        @Header("Authorization") token : String,
        @Field("Username") username: String
    ): Call<GetUserByUsernameResponse>

    @FormUrlEncoded
    @POST("/History/getHistoryByUsername")
    fun getHistoryByUsername(
        @Header("Authorization") token: String,
        @Field("Username") username: String
    ): Call<GetHistoryByUsernameResponse>

    @POST("/History/insertHistory")
    @FormUrlEncoded
    fun postHistory(
        @Header("Authorization") token : String,
        @Field("Username") username: String,
        @Field("ScanDate") scandate: String,
        @Field("Photo") photo: String,
        @Field("Jerawat") jerawat: Int,
        @Field("Kerutan") kerutan: Int,
        @Field("FlekHitam") flekhitam: Int,
        @Field("MataPanda") matapanda: Int,
        @Field("Total") total: Int,
    ): Call<InsertMLResponse>
}