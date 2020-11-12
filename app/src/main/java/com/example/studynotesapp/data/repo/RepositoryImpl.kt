package com.example.studynotesapp.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.entities.*
import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.data.local.LocalDataSource
import com.example.studynotesapp.data.remote.RemoteDataSource
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.*
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val context: Application
): Repository {

    override suspend fun register(accountRequest: AccountRequest) = withContext(Dispatchers.IO) {
        try {
            val response = remoteDataSource.register(accountRequest)
            Resource.success(response.message)

        }catch (e: Exception){
            Resource.error(e.message ?: "Check internet connection" , null)
        }


    }

    override suspend fun login(loginRequest: AccountRequest) = withContext(Dispatchers.IO){

        try {
            val response = remoteDataSource.login(loginRequest)

            when{
                response.isSuccessful -> Resource.success("Successfully logged in")
                response.code() == 401 -> Resource.error("Invalid username or password", null)
                else -> Resource.error("Unknown error", null)
            }
        }catch (e : Exception){
            Resource.error("Check network connection", e.message)
        }

    }

    override suspend fun addFolder(addFolder: AddFolder, userEmail: String): Resource<Long> = withContext(Dispatchers.IO) {
        try {
            val folderResponse = remoteDataSource.addFolder(addFolder, userEmail)
            val folderId = localDataSource.insertFolder(folderResponse.asDatabaseModel())
            Timber.i("returning resource success")
            Resource.success(folderId)
        }catch (e : Exception){
            Timber.i("returning resource error message is ${e.message}")

            Resource.error("Check network connection", -1)
        }
    }

    override suspend fun addSet(addSet: AddSet, userEmail: String) : Resource<Long> = withContext(Dispatchers.IO) {
        try {
            val setResponse = remoteDataSource.addSet(addSet, userEmail)
            val setId = localDataSource.insertSet(setResponse.asDatabaseSet())
            localDataSource.insertTerms(setResponse.terms.asDatabaseTermsList(setId))
            Resource.success(setId)

        }catch (e : Exception) {
            Timber.i(e.message)
            Resource.error("Check network connection", -1L)
        }
    }

    override suspend fun addSetsToFolder(setList: List<Set>, setIds: List<Long>, folderId: Long): Resource<String> = withContext(Dispatchers.IO){
        try {

            val setResponse = remoteDataSource.addSetToFolder(setIds, folderId)
            localDataSource.insertSetList(setList)
            localDataSource.updateFolder(setList.size, folderId)
            Resource.success(setResponse.message)


        }catch (e: Exception){
            Timber.i("The error message for addSetsToFolder is $e.message")
            Resource.error("Check internet connection", e.message)
        }
    }

    //Network Folder

    //Delete folder
    override suspend fun deleteFolder(folderId: Long): Resource<String> = withContext(Dispatchers.IO){
        try {
            val serverResponse = remoteDataSource.deleteFolder(folderId)
            val folderToDelete = localDataSource.getFolder(folderId)
            localDataSource.deleteFolder(folderToDelete)
            Resource.success(serverResponse.message)
        }catch (e : Exception){
            Resource.error("Check network connection", e.message)
        }
    }

//folders

    override suspend fun deleteFolder(folder: Folder) {
        localDataSource.deleteFolder(folder)
    }

    override fun getAllFolderSetsWithId(folderId: Long): Flow<List<FolderwithSets>> {
        return localDataSource.getFolderSetsWithId(folderId)
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return localDataSource.getAllFolders()
    }

    override fun getFolderWithId(folderId: Long):  LiveData<FolderwithSets>{
        return localDataSource.getFolderWithId(folderId)
    }

    override suspend fun getFolder(folderId: Long): Folder {
        return localDataSource.getFolder(folderId)
    }



    //sets
    override fun getAllSets(): Flow<List<Set>> {
        return localDataSource.getAllSets()
    }

    override fun getSetTermsWithId(setId: Long): LiveData<SetWithTerms> {
        return localDataSource.getSetTermsWithId(setId)
    }


    override fun getTermsWithSetId(setId: Long): Flow<List<Term>> {
        return localDataSource.getTermsWithSetId(setId)
    }

    override suspend fun insertSetList(setList: List<Set>) {
        localDataSource.insertSetList(setList)
    }
}