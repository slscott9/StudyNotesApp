package com.example.studynotesapp.ui.sets.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.data.repo.Repository

class SetListViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel(){


    private val _setList = repository.getAllSets().asLiveData(viewModelScope.coroutineContext)
    val setList : LiveData<List<Set>> = _setList


}