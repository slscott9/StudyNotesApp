package com.example.studynotesapp.login.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _loginStatus = MutableLiveData<Resource<String>>()
    val loginStatus : LiveData<Resource<String>> = _loginStatus

    fun login(userName: String, password: String){

        _loginStatus.postValue(Resource.loading(null))

        if(userName.isEmpty() || password.isEmpty()){
            _loginStatus.postValue(Resource.error("Please fill out all fields", null))
            return
        }

        viewModelScope.launch {
            val resource = repository.login(
                AccountRequest(
                    email = "",
                    userName = userName,
                    password = password
                )
            )

            _loginStatus.postValue(resource)

        }
    }
}