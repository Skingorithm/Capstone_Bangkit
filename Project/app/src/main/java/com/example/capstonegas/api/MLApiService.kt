package com.example.capstonegas.api

import androidx.lifecycle.MutableLiveData
import com.example.capstonegas.model.MLResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MLApiService {
    @FormUrlEncoded
    @POST("/input")
    fun postInput(
        @Field("input") input: String
    ): Call<MLResponse>
}