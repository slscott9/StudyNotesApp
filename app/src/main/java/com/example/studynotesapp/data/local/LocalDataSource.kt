package com.example.studynotesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.studynotesapp.data.entities.*
import com.example.studynotesapp.data.entities.Set
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {


//insert Folders
    suspend fun insertFolder(folder: Folder) : Long

//insert sets

    suspend fun insertSet(set : Set) : Long

    suspend fun insertSetList(setList: List<Set>)

    suspend fun insertTerms(termList: List<Term>)



    //get Folders
    fun getFolderSetsWithId(folderId : Long): Flow<List<FolderwithSets>>

    fun getAllFolders() : Flow<List<Folder>>

    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>

//get sets

    fun getAllSets() : Flow<List<Set>>

    fun getSetTermsWithId(setId: Long) : LiveData<SetWithTerms>



    fun getTermsWithSetId(setId: Long) : Flow<List<Term>>



}