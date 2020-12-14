package com.example.studynotesapp.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.studynotesapp.data.repo.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi

class SearchFragmentViewModel @ViewModelInject constructor(
        private val repository: Repository
) : ViewModel(){

    private val searchChanel = ConflatedBroadcastChannel<String>()

    fun setSearchQuery(searchQuery : String){
        searchChanel.offer(searchQuery)
    }

    @FlowPreview
    val setSearchResults = searchChanel.asFlow()
            .flatMapLatest { searchQuery ->  repository.getSearchedSets(searchQuery)}.asLiveData(viewModelScope.coroutineContext)
}