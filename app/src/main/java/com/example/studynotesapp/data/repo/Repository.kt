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

    suspend fun register(accountRequest: AccountRequest) : Resource<String>

    suspend fun login(loginRequest: AccountRequest) : Resource<String>


//Network add Folders
    suspend fun addFolder(addFolder: AddFolder, userEmail: String) : Resource<Long>

//Network add set

    suspend fun addSet(addSet: AddSet, userEmail: String) : Resource<Long>



//Local database get Folders

    fun getAllFolderSetsWithId(folderId : Long) : Flow<List<FolderwithSets>>

    fun getAllFolders() : Flow<List<Folder>>

    fun getFolderWithId(folderId: Long) : LiveData<FolderwithSets>

//Local Database get Sets

    fun getAllSets() : Flow<List<Set>>

    fun getSetTermsWithId(setId: Long) : LiveData<SetWithTerms>

    fun getTermsWithSetId(setId: Long) : Flow<List<Term>>







}