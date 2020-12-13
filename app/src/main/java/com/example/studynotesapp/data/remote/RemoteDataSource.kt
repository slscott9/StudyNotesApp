package com.example.studynotesapp.data.remote

import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.FolderResponse
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.SetResponse
import com.example.studynotesapp.other.Resource
import okhttp3.ResponseBody
import retrofit2.Response

interface RemoteDataSource {

    suspend fun register(accountRequest: AccountRequest) : Resource<ServerResponse>

    suspend fun login(loginRequest: AccountRequest) : Resource<ServerResponse>

    suspend fun addFolder(addFolder: AddFolder, userEmail: String) : Resource<FolderResponse>

    suspend fun addSet(addSet: AddSet, userEmail: String) : Resource<SetResponse>

    suspend fun addSetToFolder(setIds: List<Long>, folderId: Long) : Resource<ServerResponse>

    suspend fun deleteFolder(folderId: Long) : Resource<ServerResponse>

}