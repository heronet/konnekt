package com.example.konnekt.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.konnekt.network.HttpApi
import kotlinx.coroutines.launch
import retrofit2.HttpException

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

class MessageViewModel: ViewModel() {
    private var _inbox = MutableLiveData<List<Message>>()
    val inbox get(): LiveData<List<Message>> = _inbox

    private var _status = MutableLiveData<Status>(Status.LOADING)
    val status get(): LiveData<Status> = _status

    private var _conversation = MutableLiveData<List<Message>>()
    val conversation get(): LiveData<List<Message>> = _conversation


    fun getInbox(token: String) {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                _inbox.value = HttpApi.retrofitService.getInbox("Bearer $token")
                _status.value = Status.SUCCESS
            } catch (e: HttpException) {
                _status.value = Status.ERROR
                Log.d("Error", e.toString())
            }
        }
    }
    fun getConversation(token: String, username: String) {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                _conversation.value = HttpApi.retrofitService.getConversation("Bearer $token", username)
                _status.value = Status.SUCCESS
            } catch (e: HttpException) {
                _status.value = Status.ERROR
                Log.d("Error", e.toString())
            }
        }
    }

}