package com.example.studynotesapp.data.repo

import android.app.Application
import com.example.studynotesapp.CoroutineTestRule
import com.example.studynotesapp.data.local.LocalDataSource
import com.example.studynotesapp.data.remote.RemoteDataSource
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.responses.ServerResponse
import com.example.studynotesapp.other.Resource
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class RepositoryImplTest {

    private lateinit var repository: Repository

    //Collaborators
    lateinit var localDataSource : LocalDataSource
    private lateinit var remoteDataSource : RemoteDataSource
    private lateinit var serverResponse: ServerResponse
    private lateinit var serverResponseError: ServerResponse
    private lateinit var context: Application
    private lateinit var accountRequest: AccountRequest
    private lateinit var resourceError: Resource<String>
    private lateinit var resourceSuccess: Resource<String>

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup(){
        accountRequest = AccountRequest(
                userName = "newUser",
                email = "newUser@gmail.com",
                password = "password"
        )
        remoteDataSource = mock()
        localDataSource = mock()
        context = Application()
        serverResponse = ServerResponse("successful", true)
        serverResponseError = ServerResponse("failure", false)
        resourceError = Resource.error("Check internet connection", null)
        resourceSuccess = Resource.success("successful")

        repository = RepositoryImpl(localDataSource, remoteDataSource, context)
    }

    @Test
    fun assert_that_register_returns_resource_error() = runBlocking<Unit> {

        val exception = Exception()

        doAnswer { throw exception }.`when`(remoteDataSource).register(any())

        val response = repository.register(accountRequest)

        assertEquals(response, resourceError)
    }

    @Test
    fun assert_that_register_returns_resource_success() = runBlocking<Unit> {

        whenever(remoteDataSource.register(any())).thenReturn(serverResponse)

        val resource = repository.register(accountRequest)

        assertEquals(resource, resourceSuccess )
    }

    @Test
    fun login_returns_resource_error() = runBlocking<Unit> {

        val exception = Exception()
        doAnswer { throw exception }.`when`(remoteDataSource).login(any())

        val resource = repository.login(any())

        assertEquals(resource, Resource.error("Check network connection", null))

    }





}