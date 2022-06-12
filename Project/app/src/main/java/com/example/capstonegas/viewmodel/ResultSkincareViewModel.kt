package com.example.capstonegas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.ResultData
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference

class ResultSkincareViewModel(private val pref: UserPreference) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun postHistory(token: String, userName: String, data: ResultData){
        _isLoading.value = true
        //variable that contain now date
        val now = System.currentTimeMillis()
        val service = ApiConfig.getApiService().postHistory(token, userName, )
    }


    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }
}