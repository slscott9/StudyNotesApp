package com.example.studynotesapp.data.local

import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.database.StudyNotesDao
import com.example.studynotesapp.data.entities.*
import com.example.studynotesapp.data.entities.Set
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: StudyNotesDao
): LocalDataSource{


//insert Folders
    override suspend fun insertFolder(folder: Folder) : Long{
        return dao.insertFolder(folder)
    }

//Insert sets

    override suspend fun insertSet(set: Set): Long {
        return dao.insertSet(set)
    }

    override suspend fun insertTerms(termList: List<Term>) {
        dao.insertTerms(termList)
    }

    //get Folders
    override fun getFolderSetsWithId(folderId: Long): Flow<List<FolderwithSets>> {
        return dao.getFolderSetsWithId(folderId)
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return dao.getAllFolders()
    }

    override fun getFolderWithId(folderId: Long): LiveData<FolderwithSets> {
        return dao.getFolderWithId(folderId)
    }

//get Sets

    override fun getAllSets(): Flow<List<Set>> {
        return dao.getAllSets()
    }

    override fun getSetTermsWithId(setId: Long): LiveData<SetWithTerms> {
        return dao.getSetTermsWithId(setId)
    }

    override fun getTermsWithSetId(setId: Long): Flow<List<Term>> {
        return dao.getTermsWithSetId(setId)
    }
}