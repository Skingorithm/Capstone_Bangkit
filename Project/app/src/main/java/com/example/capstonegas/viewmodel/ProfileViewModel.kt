package com.example.capstonegas.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val pref: UserPreference): ViewModel() {

    private val _dataUser = MutableLiveData<Datalistuser>()
    val dataUser: LiveData<Datalistuser> = _dataUser

    private val _listHistory = MutableLiveData<List<DatalistsetHistory>>()
    val listHistory: LiveData<List<DatalistsetHistory>> = _listHistory

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun getDataUser(username: String, token: String) {
        val bearer = "Bearer $token"
        val client = ApiConfig.getApiService().getUserByUsername(bearer, username)
        client.enqueue(object : Callback<GetUserByUsernameResponse>{
            override fun onResponse(
                call: Call<GetUserByUsernameResponse>,
                response: Response<GetUserByUsernameResponse>
            ) {

                if (response.isSuccessful) {
                    _dataUser.value = response.body()?.datalistset as Datalistuser
                }
                else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetUserByUsernameResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getHistoryByUsername(username: String, token: String) {
        _isLoading.value = true
        val bearer = "Bearer $token"

        val client = ApiConfig.getApiService().getHistoryByUsername(bearer, username)
        client.enqueue(object: Callback<GetHistoryByUsernameResponse> {
            override fun onResponse(
                call: Call<GetHistoryByUsernameResponse>,
                response: Response<GetHistoryByUsernameResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    if(response.body()?.message == "success"){
                        _listHistory.value = response.body()?.datalistset as List<DatalistsetHistory>
                    } else {
                        _listHistory.value = listOf()
                    }
                } else {
                    Log.e(TAG, "response not succesfully: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetHistoryByUsernameResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}