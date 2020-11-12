package com.example.studynotesapp.data.remote

import com.example.studynotesapp.data.database.StudyNotesDao
import com.example.studynotesapp.network.StudyNotesApi
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.FolderResponse
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.SetResponse
import com.example.studynotesapp.network.utils.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val notesApi: StudyNotesApi
) : RemoteDataSource, SafeApiRequest(){

    override suspend fun register(accountRequest: AccountRequest): ServerResponse =
            apiRequest { notesApi.register(accountRequest) }


    override suspend fun login(loginRequest: AccountRequest): Response<ResponseBody> {
        return notesApi.login(loginRequest)
    }

    override suspend fun addFolder(addFolder: AddFolder, userEmail: String): FolderResponse =
            apiRequest { notesApi.addFolder(addFolder, userEmail) }


    override suspend fun addSet(addSet: AddSet, userEmail: String): SetResponse =
            apiRequest { notesApi.addSet(addSet, userEmail) }

    override suspend fun addSetToFolder(setIds: List<Long>, folderId: Long): ServerResponse  =
        apiRequest { notesApi.addSetsToFolder(setIds, folderId) }

    override suspend fun deleteFolder(folderId: Long): ServerResponse =
            apiRequest { notesApi.deleteFolder(folderId) }
}


/*
    Network send http ok but setlistFragment is resource.error
 */
