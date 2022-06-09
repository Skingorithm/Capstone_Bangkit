package com.example.capstonegas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonegas.api.ApiConfig
import com.example.capstonegas.model.UserModel
import com.example.capstonegas.model.UserPreference


class AddRoutineViewModel(private val pref: UserPreference): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun postRoutine(){
        _isLoading.value = false
//        val client = ApiConfig.getApiService().insertAlarm()
    }
}