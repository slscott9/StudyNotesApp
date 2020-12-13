package com.example.studynotesapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.studynotesapp.data.entities.*
import com.example.studynotesapp.data.entities.Set
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyNotesDao {

//insert sets
    @Insert
    suspend fun insertSet(set : Set) : Long

    @Insert
    suspend fun insertTerms(termList: List<Term>)


//insert folders

    @Insert
    suspend fun insertFolder(folder: Folder) : Long


//update set list
    @Update
    suspend fun insertSetList(setList: List<Set>)


//delete folder
    @Delete
    suspend fun deleteFolder(folder: Folder)

//update folder
    @Update
    suspend fun updateFolder(folder: Folder)


//get folders

    @Query("select * from folder_table where folderId = :folderId")
    suspend fun findFolder(folderId: Long) : Folder



    @Transaction
    @Query("select * from folder_table where folderId =:folderId")
    fun getFolderSetsWithId(folderId : Long) : Flow<List<FolderwithSets>>

    @Query("select * from folder_table")
    fun getAllFolders() : Flow<List<Folder>>


    @Transaction
    @Query("select * from folder_table where folderId = :folderId")
    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>

    @Query("select * from folder_table where folderId = :folderId")
    suspend fun getFolder(folderId: Long) : Folder

//get sets

    @Query("select * from set_table")
    fun getAllSets() : Flow<List<Set>>

    @Transaction
    @Query("select * from set_table where setId =:setId")
    fun getSetTermsWithId(setId: Long) : LiveData<SetWithTerms>

    @Query("select * from term_table where setId = :setId")
    fun getTermsWithSetId(setId: Long) : Flow<List<Term>>


}

