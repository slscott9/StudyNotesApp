package com.example.studynotesapp.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.studynotesapp.BaseFragment
import com.example.studynotesapp.R
import com.example.studynotesapp.other.Status
import com.example.studynotesapp.ui.add.viewmodels.AddFolderViewModel
import com.example.studynotesapp.ui.home.CreateOptionBottomSheetDirections
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_folder_dialog.*
import kotlinx.android.synthetic.main.fragment_create_option_bottom_sheet.*
import timber.log.Timber

@AndroidEntryPoint
class AddFolderDialogFragment : DialogFragment() {

    private val viewModel: AddFolderViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_folder_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tvOK.setOnClickListener {
            viewModel.addFolder(etFolderName.text.toString(), etDescription.text.toString())
        }

        viewModel.addFolderStatus.observe(viewLifecycleOwner){
            it?.let {
                when(it.status){
                    Status.SUCCESS -> {

                        /*
                            Since CreateOptionBottomSheet dialog fragment is the parent fragment for AddFolderDialogFragment you must access parent fragment to find nav controller
                         */
                        Timber.i("In success")
                        addFolderProgressBar.visibility = View.GONE
                        parentFragment?.findNavController()?.navigate(CreateOptionBottomSheetDirections.actionCreateOptionBottomSheetToFolderDetailFragment(it.data!!))
                        dismiss()
                    }
                    Status.LOADING -> {
                        addFolderProgressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        Timber.i("In failure")

                        addFolderProgressBar.visibility = View.GONE
                        Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }


}