package com.example.studynotesapp.ui.sets.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.studynotesapp.data.domain.asDomainSet
import com.example.studynotesapp.data.repo.Repository

class SetDetailViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel(){

    private val _setId = MutableLiveData<Long>()
    val setId : LiveData<Long> = _setId

    private val _setWithTerms = setId.switchMap {
        repository.getSetTermsWithId(it)
    }

    val setWithTerms = _setWithTerms


    fun setSetId(setId: Long) {
        _setId.value = setId
    }
}