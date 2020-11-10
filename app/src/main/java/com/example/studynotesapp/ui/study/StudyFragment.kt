package com.example.studynotesapp.ui.study

import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studynotesapp.R
import com.example.studynotesapp.databinding.FragmentStudyBinding
import com.example.studynotesapp.databinding.StudyTermItemBinding
import com.example.studynotesapp.ui.adapters.StudyTermAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudyFragment : Fragment() {

   private lateinit var binding: FragmentStudyBinding
    private val viewModel: StudyTermsViewModel by viewModels()
    private val studyFragmentArgs: StudyFragmentArgs by navArgs()
    private lateinit var studyTermsAdapter: StudyTermAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_study, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setSetId(studyFragmentArgs.setId)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        studyTermsAdapter = StudyTermAdapter(StudyTermAdapter.StudyTermListener {

        })

        viewModel.termList.observe(viewLifecycleOwner){
            studyTermsAdapter.submitList(it)
        }

        binding.rvStudyTerms.apply {
            adapter = studyTermsAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }

    }


}