package com.example.capstonegas.model

import com.google.gson.annotations.SerializedName

data class GetUserByUsernameResponse(

	@field:SerializedName("datalistset")
	val datalistset: Datalistuser,

	@field:SerializedName("status")
	val status: String
)

data class Datalistuser(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("Email")
	val email: String,

	@field:SerializedName("Username")
	val username: String,

	@field:SerializedName("FullName")
	val fullName: String,

	@field:SerializedName("ProfilePicture")
	val profilePicture: Any,

	@field:SerializedName("Password")
	val password: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
