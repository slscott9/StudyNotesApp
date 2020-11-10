package com.example.studynotesapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.repo.Repository

class HomeFragmentViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel(){

    private val _folderList = repository.getAllFolders().asLiveData(viewModelScope.coroutineContext)
    val folderList = _folderList

    private val _setList = repository.getAllSets().asLiveData(viewModelScope.coroutineContext)
    val setList = _setList


}