package com.example.studynotesapp.ui.add.viewmodels

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.getOrAwaitValue2
import com.example.studynotesapp.CoroutineTestRule
import com.example.studynotesapp.data.domain.Term
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.other.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddSetViewModelTest{

    private lateinit var viewModel: AddSetViewModel

    private lateinit var repository: Repository

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var termList : List<Term>

    private lateinit var term1Entity : com.example.studynotesapp.data.entities.Term

    private lateinit var term2Entity : com.example.studynotesapp.data.entities.Term

    private lateinit var term1Domain : Term
    private lateinit var term2Domain  : Term


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()


    @Before
    fun setup() {

        repository = mock()

        sharedPreferences = mock()

        viewModel = AddSetViewModel(repository, sharedPreferences)

        whenever(sharedPreferences.getString(any(), any())).thenReturn("User")

        term1Entity = com.example.studynotesapp.data.entities.Term(
            answer = "term 1 answer",
            question = "term 1 question",
            isSynced = true,
            setId = 1L,
            userEmail = "newUser@gmail.com"
        )

        term2Entity = com.example.studynotesapp.data.entities.Term(
            answer = "term 2 answer",
            question = "term 2 question",
            isSynced = true,
            setId = 1L,
            userEmail = "newUser@gmail.com"
        )

        term1Domain = Term(
            answer = "term 1 answer",
            term = "term 1 term"
        )

        term2Domain = Term(
            answer = "term 2 answer",
            term = "term 2 term"

        )

        termList = listOf(term1Domain, term2Domain)


    }

    @Test
    fun addSet_posts_loading_status() = runBlocking{

        val resource = Resource.success(1L)
        whenever(repository.addSet(any(), any())).thenReturn(resource)

        viewModel.addSet(termList = termList, setTitle = "new set")

        assertEquals(resource, viewModel.addSetStatus.getOrAwaitValue2())

    }

    @Test
    fun addSet_posts_error_status() = runBlocking {

        val resource = Resource.error("error", null)
        whenever(repository.addSet(any(), any())).thenReturn(resource)

        viewModel.addSet(termList = termList, setTitle = "new set")

        assertEquals(resource, viewModel.addSetStatus.getOrAwaitValue2())

    }
}