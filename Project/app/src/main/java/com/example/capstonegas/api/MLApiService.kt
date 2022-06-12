package com.example.capstonegas.api

import androidx.lifecycle.MutableLiveData
import com.example.capstonegas.model.InputModel
import com.example.capstonegas.model.MLResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MLApiService {
    @POST("/skingorithm/predict")
    fun postInput(
        @Body input: InputModel
    ): Call<MLResponse>

    @POST("/History/insertHistory")
    @FormUrlEncoded
    fun postHistory(
        @Field("Username") username: String,
        @Field("Scandate") scandate: String,
        @Field("Photo") photo: String,
        @Field("Jerawat") jerawat: Int,
        @Field("Kerutan") kerutan: Int,
        @Field("FlekHitam") flekhitam: Int,
        @Field("MataPanda") matapanda: Int,
        @Field("Total") total: Int,
    )
}