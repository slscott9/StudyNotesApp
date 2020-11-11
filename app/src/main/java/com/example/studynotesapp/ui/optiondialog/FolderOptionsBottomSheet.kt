package com.example.studynotesapp.ui.optiondialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studynotesapp.R
import com.example.studynotesapp.ui.folder.FolderDetailFragmentArgs
import com.example.studynotesapp.ui.folder.FolderDetailFragmentDirections
import com.example.studynotesapp.ui.folder.viewmodels.FolderDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_folder_options_bs.*


class FolderOptionsBottomSheet : Fragment() {

    private val navArgs: FolderOptionsBottomSheetArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folder_options_bs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()


    }


    private fun setupListeners() {
        tvEdit.setOnClickListener {

        }

        tvDelete.setOnClickListener {

        }

        tvAddSet.setOnClickListener {
            findNavController().navigate(FolderOptionsBottomSheetDirections.actionFolderOptionsBottomSheetToSetListFragment(folderId = navArgs.folderId, true))
        }
    }
}