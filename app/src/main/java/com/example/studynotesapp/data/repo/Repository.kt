package com.example.studynotesapp.data.repo

import androidx.lifecycle.LiveData
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface Repository {

    suspend fun register(accountRequest: AccountRequest) : Resource<String>

    suspend fun login(loginRequest: AccountRequest) : Resource<String>

    suspend fun addFolder(addFolder: AddFolder, userEmail: String) : Resource<Long>

    fun getAllFolderSetsWithId(folderId : Long) : Flow<List<FolderwithSets>>

    fun getAllFolders() : Flow<List<Folder>>

    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>


}