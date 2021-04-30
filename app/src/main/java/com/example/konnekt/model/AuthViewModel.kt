package com.example.konnekt.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.konnekt.model.dto.LoginDto
import com.example.konnekt.model.dto.LoginResponseDto
import com.example.konnekt.network.HttpApi
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel: ViewModel() {
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var _loginResponse = MutableLiveData<LoginResponseDto>()
    val loginResponse get(): LiveData<LoginResponseDto> = _loginResponse

    private var _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess get(): LiveData<Boolean> = _loginSuccess

    private lateinit var _errorMessage: String
    val errorMessage get(): String = _errorMessage

    fun onLogin(email: String, password: String) {
        val loginDto: LoginDto = LoginDto(email, password)
        viewModelScope.launch {
            try {
                _loginResponse.value = HttpApi.retrofitService.login(loginDto)
                _loginSuccess.value = true
            } catch (e: HttpException) {
                _errorMessage = e.response()?.message().toString()
                _loginSuccess.value = false
            }
        }
    }
}