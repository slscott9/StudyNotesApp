package com.example.studynotesapp.ui.sets

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studynotesapp.R
import com.example.studynotesapp.data.domain.asDomainSet
import com.example.studynotesapp.databinding.FragmentSetDetailBinding
import com.example.studynotesapp.ui.adapters.TermListAdapter
import com.example.studynotesapp.ui.sets.viewmodels.SetDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.card_view.view.*

@AndroidEntryPoint
class SetDetailFragment : Fragment() {

    private lateinit var binding : FragmentSetDetailBinding
    private val setDetailArgs: SetDetailFragmentArgs by navArgs()
    private val viewModel: SetDetailViewModel by viewModels()
    private lateinit var termListAdapter: TermListAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setSetId(setDetailArgs.setId)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpToolbar()
        setupRv()
        setupObservers()
        setupListeners()
        binding.cvFlashCard.tvTitle.text = "FLASHCARDS"
        binding.cvWrite.tvTitle.text = "WRITE"


    }

    private fun setupListeners() {
        binding.cvFlashCard.setOnClickListener {
            findNavController().navigate(
                SetDetailFragmentDirections.actionSetDetailFragmentToStudyFragment(
                    setDetailArgs.setId
                )
            )
        }

//        binding.setDetailToolbar.setOnMenuItemClickListener {
//            when(it.itemId){
//
//                R.id.set_detail_options_item -> {
//
//                }
//                else -> false
//            }
//        }




    }

    private fun setUpToolbar() {
        binding.setDetailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {

        viewModel.setWithTerms.observe(viewLifecycleOwner){
            binding.set = it.set.asDomainSet()
            termListAdapter.submitList(it.termList)
        }
    }

    private fun setupRv(){
        termListAdapter = TermListAdapter(TermListAdapter.TermListListener {

        })

        binding.rvTermList.apply {
            adapter = termListAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }


}