package com.example.studynotesapp.ui.sets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studynotesapp.R
import com.example.studynotesapp.data.domain.asDomainSetList
import com.example.studynotesapp.databinding.FragmentSetListBinding
import com.example.studynotesapp.databinding.SetListItemBinding
import com.example.studynotesapp.ui.adapters.SetListAdapter
import com.example.studynotesapp.ui.adapters.SetListListener
import com.example.studynotesapp.ui.sets.viewmodels.SetListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetListFragment : Fragment() {

    private lateinit var binding: FragmentSetListBinding
    private val viewModel : SetListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val setListAdapter = SetListAdapter(SetListListener {
            findNavController().navigate(SetListFragmentDirections.actionSetListFragmentToSetDetailFragment(it))
        })

        viewModel.setList.observe(viewLifecycleOwner){
            setListAdapter.submitList(it.asDomainSetList())
        }

        binding.rvSetListFragment.apply {
            adapter = setListAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }


    }


}