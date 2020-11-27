package com.example.studynotesapp.data.repo

import android.app.Application
import com.example.studynotesapp.CoroutineTestRule
import com.example.studynotesapp.data.local.LocalDataSource
import com.example.studynotesapp.data.remote.RemoteDataSource
import com.example.studynotesapp.network.dto.requests.AccountRequest
import com.example.studynotesapp.network.dto.requests.AddFolder
import com.example.studynotesapp.network.dto.responses.FolderResponse
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

    @Test
    fun addFolder_returns_error() = runBlocking<Unit> {
        val exception = Exception()

        val errorResource = Resource.error("Check network connection", -1L)

        doAnswer { throw exception }.`when`(remoteDataSource).addFolder(any(), any())

        val response = repository.addFolder(any(), any())


        //assertEquals(expected, actual)
        assertEquals(errorResource, response )
    }

    @Test
    fun addFolder_calls_insertFolder_onSuccess() = runBlocking<Unit> {

        val folderResponse = FolderResponse(
                id = 1,
                folderName = "new folder",
                userEmail = "newUser@gmail.com",
                description = "new folder description",
                isSynced = false,
                setCount = "1",
                userName = "newUser"
        )

        val addFolder = AddFolder(
                folderName = "new folder",
                userEmail = "newUser@gmail.com",
                description = "new folder description",
                isSynced = false,
                setCount = "1",
                userName = "newUser"
        )

        val successResource = Resource.success(1L)

        whenever(remoteDataSource.addFolder(any(), any())).thenReturn(folderResponse)
        whenever(localDataSource.insertFolder(any())).thenReturn(1L)

        val resource = repository.addFolder(addFolder, "newUser@gmail.com")


        /*
            remoteDataSource is mocked so we can user any for its parameters

            IMPORTANT: repository is NOT mocked so we cannot use any for its parameters
         */
        verify(remoteDataSource, times(1)).addFolder(any(), any())
        assertEquals(successResource, resource)


    }







}