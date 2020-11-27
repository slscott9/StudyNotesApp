package com.example.studynotesapp.login.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.getOrAwaitValue2
import com.example.studynotesapp.CoroutineTestRule
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.other.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    //collaborators
    private lateinit var repository: Repository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()



    @Before
    fun setup() {
        repository = mock()

        viewModel = LoginViewModel(repository)


    }


    //login posts resource error when username or password is empty
    @Test
    fun login() = runBlockingTest {

        viewModel.login("", "")


        assertEquals(Resource.error("Please fill out all fields", null), viewModel.loginStatus.getOrAwaitValue2())

    }

    @Test
    fun login_posts_resource_success_whenever_repository_login_called() = runBlockingTest {

        whenever(repository.login(any())).thenReturn(Resource.success(null))

        viewModel.login("username", "password")

        assertEquals(Resource.success(null), viewModel.loginStatus.getOrAwaitValue2())


    }





}