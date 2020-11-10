package com.example.studynotesapp.ui.add.viewmodels

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.other.Constants
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.launch

class AddFolderViewModel @ViewModelInject constructor(
    private val repository: Repository,
    private val sharedPreferences: SharedPreferences
) : ViewModel(){

    private val _addFolderStatus = MutableLiveData<Resource<Long>>()
    val addFolderStatus : LiveData<Resource<Long>> = _addFolderStatus

    private val userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL)
    private val userName = sharedPreferences.getString(Constants.KEY_LOGGED_IN_USERNAME, Constants.NO_USERNAME)

    fun addFolder(folderName: String, description: String?){

        _addFolderStatus.postValue(Resource.loading(null))

        viewModelScope.launch {
            val response = repository.addFolder(
                AddFolder(
                    folderName = folderName,
                    userEmail = userEmail ?: "",
                    userName = userName ?: "",
                    description = description,
                    isSynced = false,
                    termCount = "0"

                ),
                userEmail ?: ""
            )

            _addFolderStatus.postValue(response)
        }
    }
}