package com.example.studynotesapp.ui.folder.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.repo.Repository

class FolderListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _folders = repository.getAllFolders().asLiveData(viewModelScope.coroutineContext)
    val folders : LiveData<List<Folder>> = _folders



}