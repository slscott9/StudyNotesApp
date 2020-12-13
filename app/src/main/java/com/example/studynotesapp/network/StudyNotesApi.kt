package com.example.studynotesapp.network

import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.FolderResponse
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.SetResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface StudyNotesApi {

    @POST("/register")
    suspend fun register(@Body registerRequest: AccountRequest) :ServerResponse

    @POST("/login")
    suspend fun login(@Body loginRequest: AccountRequest) : ServerResponse


    @POST("/user/add/folder/{userEmail}")
    suspend fun addFolder(@Body addFolder: AddFolder, @Path("userEmail") userEmail: String) : FolderResponse


    @POST("user/add/set/{userEmail}")
    suspend fun addSet(@Body addSet: AddSet, @Path("userEmail") userEmail: String) : SetResponse

    @POST("/user/add/set/folder/{folderId}")
    suspend fun addSetsToFolder(@Body setIds : List<Long>, @Path("folderId") folderId: Long) : ServerResponse


    @POST("user/delete/folder/{folderId}")
    suspend fun deleteFolder(@Path("folderId") folderId: Long) : ServerResponse

}

