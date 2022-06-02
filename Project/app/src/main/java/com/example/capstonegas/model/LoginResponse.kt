package com.example.capstonegas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("token")
    val accessToken: String? = null,
) : Parcelable
