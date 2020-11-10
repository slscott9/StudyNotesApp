package com.example.studynotesapp.data.remote

import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.FolderResponse
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.SetResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface RemoteDataSource {

    suspend fun register(accountRequest: AccountRequest) : ServerResponse

    suspend fun login(loginRequest: AccountRequest) : Response<ResponseBody>

    suspend fun addFolder(addFolder: AddFolder, userEmail: String) : FolderResponse

    suspend fun addSet(addSet: AddSet, userEmail: String) : SetResponse

}