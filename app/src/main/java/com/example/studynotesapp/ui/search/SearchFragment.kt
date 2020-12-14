package com.example.studynotesapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studynotesapp.R
import com.example.studynotesapp.data.domain.asDomainSetList
import com.example.studynotesapp.ui.adapters.SetListAdapter
import com.example.studynotesapp.ui.adapters.SetListListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchFragmentViewModel by viewModels()
    private lateinit var setListAdapter: SetListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListAdapter = SetListAdapter(SetListListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSetDetailFragment(it))
        })

        rvSetList.adapter = setListAdapter

        setupListeners()
        setupObservers()

    }


    private fun setupListeners() {
        searchSetsView.setOnQueryTextListener(
                object : android.widget.SearchView.OnQueryTextListener,
                        androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let { viewModel.setSearchQuery(it) } //as user types searchQuery it is offered to subscribers which triggers repo.getSearchCemList
                        return true
                    }
                }
        )
    }

    private fun setupObservers() {

        viewModel.setSearchResults.observe(viewLifecycleOwner){
            setListAdapter.submitList(it.asDomainSetList())
        }
    }


}