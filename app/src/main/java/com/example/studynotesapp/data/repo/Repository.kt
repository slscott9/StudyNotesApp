package com.example.studynotesapp.data.repo

import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.studynotesapp.data.entities.*
import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.requests.AddSet
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.network.dto.responses.SetResponse
import com.example.studynotesapp.other.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface Repository {


//Network register and login

    suspend fun register(accountRequest: AccountRequest) : Resource<ServerResponse>

    suspend fun login(loginRequest: AccountRequest) : Resource<ServerResponse>


//Network add Folders
    suspend fun addFolder(addFolder: AddFolder, userEmail: String) : Resource<Long>

//Network delete folder
    suspend fun deleteFolder(folderId: Long) : Resource<String>

//Network add set

    suspend fun addSet(addSet: AddSet, userEmail: String) : Resource<Long>

    suspend fun addSetsToFolder(setList : List<Set>, setIds: List<Long>, folderId: Long) : Resource<String>



//Local database get Folders

    fun getAllFolderSetsWithId(folderId : Long) : Flow<List<FolderwithSets>>

    fun getAllFolders() : Flow<List<Folder>>

    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>

    suspend fun getFolder(folderId: Long) : Folder


    //local delete folder
    suspend fun deleteFolder(folder: Folder)

//Local Database get Sets

    fun getAllSets() : Flow<List<Set>>

    fun getSetTermsWithId(setId: Long) : LiveData<SetWithTerms>

    fun getTermsWithSetId(setId: Long) : Flow<List<Term>>

    fun getSearchedSets(searchQuery: String) : Flow<List<Set>>


//insert sets
    suspend fun insertSetList(setList : List<Set>)








}