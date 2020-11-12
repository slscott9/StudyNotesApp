package com.example.studynotesapp.ui.folder.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import com.example.studynotesapp.data.repo.Repository
import kotlinx.coroutines.launch

class FolderDetailViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(){


    private val _folderId = MutableLiveData<Long>()
    val folderId : LiveData<Long> = _folderId

    private val _folder = _folderId.switchMap {
        repository.getFolderWithId(it)
    }

    val folder : LiveData<FolderwithSets> = _folder


//    private val _foldersSets = _folder.switchMap {
//        liveData (viewModelScope.coroutineContext){
//            emit(it.setList)
//        }
//    }

    fun setFolderId(folderId : Long){
        _folderId.value = folderId
    }





}