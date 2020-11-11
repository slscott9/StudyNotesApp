package com.example.studynotesapp.ui.sets.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.studynotesapp.data.domain.DomainSet
import com.example.studynotesapp.data.domain.asDatabaseSets
import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.launch

class SetListViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel(){


    private val _setList = repository.getAllSets().asLiveData(viewModelScope.coroutineContext)
    val setList : LiveData<List<Set>> = _setList

    private val _insertSetStatus = MutableLiveData<Resource<String>>()
    val insertSetStatus : LiveData<Resource<String>> = _insertSetStatus

    fun addSetsToFolder(setsToAdd: List<DomainSet>, folderId: Long){

        _insertSetStatus.postValue(Resource.loading(null))
        val databaseSets = setsToAdd.asDatabaseSets(folderId)

        val setIdList = setsToAdd.map {
            it.setId
        }

        viewModelScope.launch {

            val resource = repository.addSetsToFolder(databaseSets, setIdList, folderId)

            _insertSetStatus.postValue(resource)

        }



    }


}