package com.example.studynotesapp.ui.add.viewmodels

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.domain.Term
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.requests.asAddTermList
import com.example.studynotesapp.other.Constants
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.launch

class AddSetViewModel @ViewModelInject constructor(
        private val repository: Repository,
        sharedPreferences: SharedPreferences
) : ViewModel(){



    private val _addSetStatus = MutableLiveData<Resource<Long?>>()
    val addSetStatus : LiveData<Resource<Long?>> = _addSetStatus

    private val userEmail = sharedPreferences.getString(Constants.KEY_LOGGED_IN_EMAIL, Constants.NO_EMAIL)
    private val userName = sharedPreferences.getString(Constants.KEY_LOGGED_IN_USERNAME, Constants.NO_USERNAME)


    fun addSet(setTitle: String, termList: List<Term>) {

        _addSetStatus.postValue(Resource.loading(null))

        viewModelScope.launch {
            val resource = repository.addSet(
                    AddSet(
                            setName = setTitle,
                            userEmail = userEmail ?: "",
                            userName = userName ?: "",
                            termCount = termList.size,
                            isSynced = false,
                            folderId = null,
                            terms = termList.asAddTermList(userEmail ?: "")

                    ),
                    userEmail ?: ""
            )

            _addSetStatus.postValue(resource)
        }

    }
}