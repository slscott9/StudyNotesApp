package com.example.studynotesapp.data.local

import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import com.example.studynotesapp.data.entities.Set
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {


//insert Folders
    suspend fun insertFolder(folder: Folder) : Long


//get Folders
    fun getFolderSetsWithId(folderId : Long): Flow<List<FolderwithSets>>

    fun getAllFolders() : Flow<List<Folder>>

    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>


//get sets

    fun getAllSets() : Flow<List<Set>>


}