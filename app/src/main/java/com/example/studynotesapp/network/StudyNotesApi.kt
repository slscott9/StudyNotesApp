package com.example.studynotesapp.network

import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.responses.FolderResponse
import com.example.studynotesapp.network.dto.responses.ServerResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyNotesApi {

    @POST("/register")
    suspend fun register(@Body registerRequest: AccountRequest) : Response<ServerResponse>

    @POST("/login")
    suspend fun login(@Body loginRequest: AccountRequest) : Response<ResponseBody>


    @POST("/user/addFolder/{userEmail}")
    suspend fun addFolder(@Body addFolder: AddFolder, @Path("userEmail") userEmail: String) : Response<FolderResponse>

}

