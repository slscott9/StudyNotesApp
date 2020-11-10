package com.example.studynotesapp.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import com.example.studynotesapp.data.local.LocalDataSource
import com.example.studynotesapp.data.remote.RemoteDataSource
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.asDatabaseModel
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

    override fun getAllFolderSetsWithId(folderId: Long): Flow<List<FolderwithSets>> {
        return localDataSource.getFolderSetsWithId(folderId)
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return localDataSource.getAllFolders()
    }

    override fun getFolderWithId(folderId: Long): LiveData<FolderwithSets> {
        return localDataSource.getFolderWithId(folderId)
    }
}