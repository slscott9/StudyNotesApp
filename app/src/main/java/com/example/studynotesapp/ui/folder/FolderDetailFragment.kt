package com.example.studynotesapp.ui.folder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studynotesapp.R
import com.example.studynotesapp.data.domain.asDomainFolder
import com.example.studynotesapp.data.domain.asDomainSetList
import com.example.studynotesapp.databinding.FragmentFolderDetailBinding
import com.example.studynotesapp.ui.adapters.SetListAdapter
import com.example.studynotesapp.ui.adapters.SetListListener
import com.example.studynotesapp.ui.folder.viewmodels.FolderDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FolderDetailFragment : Fragment() {

    private lateinit var binding : FragmentFolderDetailBinding
    private val viewmodel : FolderDetailViewModel by viewModels()
    private val folderDetailArgs : FolderDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_folder_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.setFolderId(folderDetailArgs.folderId)

        val setListAdapter = SetListAdapter(SetListListener {
            findNavController().navigate(FolderDetailFragmentDirections.actionFolderDetailFragmentToSetDetailFragment(it))
        })


        viewmodel.folder.observe(viewLifecycleOwner){
            binding.domainFolder = it.folder.asDomainFolder()
            setListAdapter.submitList(it.setList.asDomainSetList())

        }

        binding.rvFolderDetail.apply {
            adapter = setListAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }


}