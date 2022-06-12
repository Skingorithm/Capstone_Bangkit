package com.example.capstonegas.model

import com.google.gson.annotations.SerializedName

data class GetAlarmResponse(

	@field:SerializedName("datalistset")
	val datalistset: List<AlarmItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class AlarmItem(

	@field:SerializedName("NotifyHour")
	val notifyHour: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("RoutinityName")
	val routinityName: String? = null,

	@field:SerializedName("Username")
	val username: String? = null,

	@field:SerializedName("fifteenBefore")
	val fifteenBefore: Boolean? = null,

	@field:SerializedName("thirtyBefore")
	val thirtyBefore: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("AlarmDate")
	val alarmDate: String? = null,

	@field:SerializedName("repeatAlarm")
	val repeatAlarm: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
