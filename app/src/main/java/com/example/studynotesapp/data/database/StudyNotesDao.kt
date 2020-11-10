package com.example.studynotesapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import com.example.studynotesapp.data.entities.Set
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyNotesDao {


//insert folders

    @Insert
    suspend fun insertFolder(folder: Folder) : Long

//get folders

    @Query("select * from folder_table where folderId =:folderId")
    fun getFolderSetsWithId(folderId : Long) : Flow<List<FolderwithSets>>

    @Query("select * from folder_table")
    fun getAllFolders() : Flow<List<Folder>>

    @Query("select * from folder_table where folderId = :folderId")
    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>

//get sets

    @Query("select * from set_table")
    fun getAllSets() : Flow<List<Set>>
}

