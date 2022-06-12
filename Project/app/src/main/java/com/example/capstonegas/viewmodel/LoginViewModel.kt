package com.example.capstonegas.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.LoginResponse
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference): ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean?>()
    val loginSuccess: MutableLiveData<Boolean?> = _loginSuccess

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postLogin(userName: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().loginUser(userName, password)
        client.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse>,
                response: retrofit2.Response<LoginResponse>
            ) {
                if(response.isSuccessful){
                    val loginResponse = response.body()
                    if(loginResponse != null){
                        if(loginResponse.message != "error") {
                            Log.d(TAG, "Hasil onResponse: Tidak Error")
                            _loginSuccess.value = true
                            val token = loginResponse.accessToken
                            if (token != null) {
                                login(userName, token)
                            }
                        }
                        else{
                            Log.d(TAG, "Hasil onResponse: Error")
                            _loginSuccess.value = false
                        }
                    }
                    else{
                        _loginSuccess.value = false
                    }
                }
                else if(response.code() == 401){
                    _loginSuccess.value = false
                }
                _isLoading.value = false
            }
            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _isLoading.value = false
            }
        })
        _loginSuccess.value = null
    }

    fun login(userName: String, token: String) {
        viewModelScope.launch {
            pref.login(userName, token)
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}