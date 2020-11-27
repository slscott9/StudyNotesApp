package com.example.studynotesapp.ui.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.studynotesapp.R
import com.example.studynotesapp.ui.add.AddFolderDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_option_bottom_sheet.*

@AndroidEntryPoint
class CreateOptionBottomSheet : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_option_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        tvCreateSet.setOnClickListener {
            findNavController().navigate(CreateOptionBottomSheetDirections.actionCreateOptionBottomSheetToAddSetFragment())
            dismiss()
        }

        tvCreateFolder.setOnClickListener {

            val addFolderDialogFragment = AddFolderDialogFragment()
            addFolderDialogFragment.show(parentFragmentManager, addFolderDialogFragment.tag)
        }
    }


}