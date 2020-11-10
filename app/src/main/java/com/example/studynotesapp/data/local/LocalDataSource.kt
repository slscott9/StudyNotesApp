package com.example.studynotesapp.data.local

import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertFolder(folder: Folder) : Long

    fun getFolderSetsWithId(folderId : Long): Flow<List<FolderwithSets>>

    fun getAllFolders() : Flow<List<Folder>>

    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>




}