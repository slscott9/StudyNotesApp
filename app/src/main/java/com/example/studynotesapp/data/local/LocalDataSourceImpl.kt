package com.example.studynotesapp.data.local

import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.database.StudyNotesDao
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: StudyNotesDao
): LocalDataSource{

    override suspend fun insertFolder(folder: Folder) : Long{
        return dao.insertFolder(folder)
    }

    override fun getFolderSetsWithId(folderId: Long): Flow<List<FolderwithSets>> {
        return dao.getFolderSetsWithId(folderId)
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return dao.getAllFolders()
    }

    override fun getFolderWithId(folderId: Long): LiveData<FolderwithSets> {
        return dao.getFolderWithId(folderId)
    }
}