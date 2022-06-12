package com.example.capstonegas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.AlarmItem
import com.example.capstonegas.model.GetAlarmResponse
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleViewModel(private val pref: UserPreference) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listAlarm = MutableLiveData<List<AlarmItem>?>()
    val listAlarm: MutableLiveData<List<AlarmItem>?> = _listAlarm

    private val _isExist = MutableLiveData<Boolean>()
    val isExist: LiveData<Boolean> = _isExist

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun postAlarm(token : String, userName: String, alarmDate: String) {
        _isLoading.value = true
        val service = ApiConfig.getApiService().getAlarmByDate("Bearer $token", userName, alarmDate)
        service.enqueue(object : Callback<GetAlarmResponse> {
            override fun onResponse(
                call: Call<GetAlarmResponse>,
                response: Response<GetAlarmResponse>
            ) {
                if(response.isSuccessful()){
                    if(response.body()?.status == "success"){
                        _listAlarm.value = response.body()?.datalistset as List<AlarmItem>?
                        _isLoading.value = false
                        _isExist.value = true
                    }
                    else{
                        _isLoading.value = false
                        _isExist.value = false
                    }
                }
            }

            override fun onFailure(call: Call<GetAlarmResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}