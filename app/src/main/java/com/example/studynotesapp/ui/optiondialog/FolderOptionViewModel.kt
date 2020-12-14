package com.example.studynotesapp.ui.optiondialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.launch

class FolderOptionViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel(){

    private val _deleteResponse = MutableLiveData<Resource<String>>()
    val deleteResponse : LiveData<Resource<String>> = _deleteResponse


    fun deleteFolder(folderId: Long){

        _deleteResponse.postValue(Resource.loading(null))

        viewModelScope.launch {

            val resource = repository.deleteFolder(folderId)

            _deleteResponse.postValue(resource)

        }
    }
}