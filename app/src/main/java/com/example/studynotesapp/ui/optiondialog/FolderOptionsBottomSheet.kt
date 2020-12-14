package com.example.studynotesapp.ui.optiondialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studynotesapp.R
import com.example.studynotesapp.other.Status
import com.example.studynotesapp.ui.folder.FolderDetailFragmentArgs
import com.example.studynotesapp.ui.folder.FolderDetailFragmentDirections
import com.example.studynotesapp.ui.folder.viewmodels.FolderDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_folder_options_bs.*

@AndroidEntryPoint
class FolderOptionsBottomSheet : Fragment() {

    private val navArgs: FolderOptionsBottomSheetArgs by navArgs()
    private val folderOptionsViewModel: FolderOptionViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folder_options_bs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        folderOptionsViewModel.deleteResponse.observe(viewLifecycleOwner){
            it?.let {
                when(it.status){
                    Status.SUCCESS -> {
                        folderOptionProgressBar.visibility = View.GONE
                        findNavController().navigate(R.id.folderListFragment)
                    }
                    Status.ERROR -> {
                        folderOptionProgressBar.visibility = View.GONE
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        folderOptionProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        }


    }


    private fun setupListeners() {
        tvEdit.setOnClickListener {

        }

        tvDelete.setOnClickListener {
            folderOptionsViewModel.deleteFolder(navArgs.folderId)
//            findNavController().navigate(R.id.folderListFragment)
        }

        tvAddSet.setOnClickListener {
            findNavController().navigate(FolderOptionsBottomSheetDirections.actionFolderOptionsBottomSheetToSetListFragment(folderId = navArgs.folderId, true))
        }

        btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}