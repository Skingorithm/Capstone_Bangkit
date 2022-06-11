package com.example.capstonegas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.InsertAlarmResponse
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddRoutineViewModel(private val pref: UserPreference): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun postRoutine(token: String, routinityName: String, notifyHour: String, alarmDate: String,
                    fifteenBefore: Boolean, thirtyBefore:Boolean, repeatValue: Int, userName: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().insertAlarm(token, routinityName, notifyHour, alarmDate, fifteenBefore, thirtyBefore, repeatValue, userName)
        client.enqueue(object: Callback<InsertAlarmResponse>{
            override fun onResponse(
                call: Call<InsertAlarmResponse>,
                response: Response<InsertAlarmResponse>
            ) {
                if(response.isSuccessful){
                    _isSuccess.value = response.body()?.status == "success"
                    _isLoading.value = false
                }
                else{
                    _isSuccess.value = false
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<InsertAlarmResponse>, t: Throwable) {
                _isSuccess.value = false
            }
        })
    }
}