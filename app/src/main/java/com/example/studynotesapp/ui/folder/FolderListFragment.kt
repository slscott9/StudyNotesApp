package com.example.studynotesapp.ui.folder

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
import com.example.studynotesapp.data.domain.asDomainFolderList
import com.example.studynotesapp.databinding.FolderListItemBinding
import com.example.studynotesapp.databinding.FragmentFolderListBinding
import com.example.studynotesapp.ui.adapters.FolderListAdapter
import com.example.studynotesapp.ui.folder.viewmodels.FolderListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderListFragment : Fragment() {

    private lateinit var binding : FragmentFolderListBinding
    private val viewModel : FolderListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_folder_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val folderListAdapter = FolderListAdapter(FolderListAdapter.FolderListListener {
            findNavController().navigate(FolderListFragmentDirections.actionFolderListFragmentToFolderDetailFragment(it))
        })


        viewModel.folders.observe(viewLifecycleOwner){
            folderListAdapter.submitList(it.asDomainFolderList())
        }

        binding.rvFolderList.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = folderListAdapter
        }
    }


}