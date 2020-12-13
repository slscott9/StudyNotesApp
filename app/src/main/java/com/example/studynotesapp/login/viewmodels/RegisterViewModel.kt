package com.example.studynotesapp.login.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _registerStatus = MutableLiveData<Resource<ServerResponse>>()
    val registerStatus: LiveData<Resource<ServerResponse>> = _registerStatus

    fun register(userEmail: String, userName: String, password : String) {

        _registerStatus.postValue(Resource.loading(null))

        if(userEmail.isEmpty() || password.isEmpty() || userName.isEmpty()){
            _registerStatus.postValue(Resource.error("Please fill in all fields", null))
            return
        }

        viewModelScope.launch {
            val resource = repository.register(
                AccountRequest(
                    userName = userName,
                    email = userEmail,
                    password = password
                )
            )

            _registerStatus.postValue(resource)
        }
    }



}