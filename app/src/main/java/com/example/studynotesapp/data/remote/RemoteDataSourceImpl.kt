package com.example.studynotesapp.data.remote

import com.example.studynotesapp.data.database.StudyNotesDao
import com.example.studynotesapp.network.StudyNotesApi
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.FolderResponse
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.SetResponse
import com.example.studynotesapp.network.utils.ResponseHandler
import com.example.studynotesapp.network.utils.SafeApiRequest
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val notesApi: StudyNotesApi,
    private val responseHandler: ResponseHandler
) : RemoteDataSource, SafeApiRequest() {

    override suspend fun register(accountRequest: AccountRequest): Resource<ServerResponse> {
        return try {
            responseHandler.handleSuccess { notesApi.register(accountRequest) }
        }catch (e : Exception){
            responseHandler.handleException(e)
        }
    }


    override suspend fun login(loginRequest: AccountRequest): Resource<ServerResponse> {

        return try {
            responseHandler.handleSuccess { notesApi.login(loginRequest) }
        }catch (e : Exception){
            responseHandler.handleException(e)
        }
    }

    override suspend fun addFolder(addFolder: AddFolder, userEmail: String): Resource<FolderResponse>  {

        return try {
            responseHandler.handleSuccess {  notesApi.addFolder(addFolder, userEmail) }
        }catch (e : Exception){
            responseHandler.handleException(e)
        }

    }


    override suspend fun addSet(addSet: AddSet, userEmail: String): Resource<SetResponse> {
        return try {
            responseHandler.handleSuccess { notesApi.addSet(addSet, userEmail) }
        }catch (e : Exception){
            responseHandler.handleException(e)
        }
    }

    override suspend fun addSetToFolder(setIds: List<Long>, folderId: Long): Resource<ServerResponse> {
        return try {
            responseHandler.handleSuccess { notesApi.addSetsToFolder(setIds, folderId) }
        }catch (e : Exception){
            responseHandler.handleException(e)
        }
    }
    override suspend fun deleteFolder(folderId: Long): Resource<ServerResponse> {
        return try {
            responseHandler.handleSuccess {  notesApi.deleteFolder(folderId) }
        }catch (e : Exception){
            responseHandler.handleException(e)
        }
    }
}


/*
    Network send http ok but setlistFragment is resource.error
 */
