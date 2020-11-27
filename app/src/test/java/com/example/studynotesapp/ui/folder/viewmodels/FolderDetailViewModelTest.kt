package com.example.studynotesapp.ui.folder.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.getOrAwaitValue2
import com.example.studynotesapp.CoroutineTestRule
import com.example.studynotesapp.data.entities.Folder
import com.example.studynotesapp.data.entities.FolderwithSets
import com.example.studynotesapp.data.entities.Set
import com.example.studynotesapp.data.repo.Repository
import com.example.studynotesapp.other.Resource
import com.google.common.collect.Sets
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FolderDetailViewModelTest {

    private lateinit var viewModel: FolderDetailViewModel

    private lateinit var repository: Repository

    private lateinit var observer: Observer<FolderwithSets>

    private lateinit var folderwithSets: FolderwithSets

    private lateinit var folder : Folder

    private lateinit var set1 : Set
    private lateinit var set2 : Set

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {

        repository = mock()
        viewModel = FolderDetailViewModel(repository)

        observer = mock()

        folder  = Folder(
                folderId = 1L,
                description = "description",
                isSynced = true,
                setCount = "0",
                name = "New Folder",
                userEmail = "newUser@gmail.com",
                userName = "newUser"
        )

        set1 = Set(
                setId = 1L,
                folderId = 1L,
                setName = "new set",
                isSynced = true,
                termCount = 0,
                userName = "newUser",
                userEmail = "newUser@gmail.com"
        )

        set2 = Set(
                setId = 2L,
                folderId = 1L,
                setName = "new set 2",
                isSynced = true,
                termCount = 0,
                userName = "newUser",
                userEmail = "newUser@gmail.com"
        )

        folderwithSets = FolderwithSets(
                folder = Folder(
                        folderId = 1L,
                        description = "description",
                        isSynced = true,
                        setCount = "0",
                        name = "new Folder",
                        userEmail = "newUser@gmail.com",
                        userName = "newUser"
                ),
                listOf(set1, set2)
        )

        viewModel.folder.observeForever(observer)

    }

    @Test
    fun setFolderId_triggers_tranformation_folder() = runBlockingTest {



        val folderSetLiveData = MutableLiveData(folderwithSets)

        whenever(repository.getFolderWithId(any())).thenReturn(folderSetLiveData)

        viewModel.setFolderId(1L)

        assertEquals(1L, viewModel.folderId.getOrAwaitValue2())

        assertEquals(folderwithSets,viewModel.folder.getOrAwaitValue2() )



    }

    @Test
    fun setFolderId_sets_folderId_live_data() {

        viewModel.setFolderId(1L)

        assertEquals(1L, viewModel.folderId.getOrAwaitValue2())


    }


}