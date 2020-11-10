package com.example.studynotesapp.ui.study

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.studynotesapp.data.repo.Repository
import kotlinx.coroutines.flow.map

class StudyTermsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(){

    private val _setId = MutableLiveData<Long>()
    val setId : LiveData<Long> = _setId

    private val _termList = _setId.switchMap {
        repository.getTermsWithSetId(it).asLiveData(viewModelScope.coroutineContext)
    }

    val termList = _termList



    fun setSetId(setId : Long) {
        _setId.value = setId
    }
}