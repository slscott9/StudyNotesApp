package com.example.studynotesapp.ui.sets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studynotesapp.R
import com.example.studynotesapp.data.domain.asDomainSetList
import com.example.studynotesapp.databinding.FragmentSetListBinding
import com.example.studynotesapp.databinding.SetListItemBinding
import com.example.studynotesapp.other.Status
import com.example.studynotesapp.ui.adapters.MyItemDetailsLookup
import com.example.studynotesapp.ui.adapters.SetListAdapter
import com.example.studynotesapp.ui.adapters.SetListAddFolderAdapter
import com.example.studynotesapp.ui.adapters.SetListListener
import com.example.studynotesapp.ui.sets.viewmodels.SetListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SetListFragment : Fragment() {

    private lateinit var binding: FragmentSetListBinding
    private val viewModel : SetListViewModel by viewModels()
    private val setListArgs: SetListFragmentArgs by navArgs()
    private lateinit var setListAdapter: SetListAdapter
    var tracker: SelectionTracker<Long>? = null



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

        if(setListArgs.addSetToFolderFlag){
            binding.setListToolbar.menu.findItem(R.id.createSetMenuItem).setIcon(R.drawable.ic_baseline_check_24)



            val setListAdapter = SetListAddFolderAdapter()


            val rv = binding.rvSetListFragment
            rv.layoutManager = LinearLayoutManager(requireActivity())
            rv.adapter = setListAdapter

            tracker = SelectionTracker.Builder<Long>(
                "selection",
                rv,
                StableIdKeyProvider(rv),
                MyItemDetailsLookup(rv),
                StorageStrategy.createLongStorage()

            ).withSelectionPredicate(
                SelectionPredicates.createSelectAnything()
            ).build()

            setListAdapter.tracker = tracker

            viewModel.setList.observe(viewLifecycleOwner){
                setListAdapter.submitList(it.asDomainSetList())
            }

            viewModel.insertSetStatus.observe(viewLifecycleOwner){
                it?.let {
                    when(it.status){
                        Status.SUCCESS -> {
                            binding.setListProgressBar.visibility = View.GONE
                            findNavController().navigate(SetListFragmentDirections.actionSetListFragmentToFolderDetailFragment(setListArgs.folderId))
                        }
                        Status.LOADING -> {
                            binding.setListProgressBar.visibility = View.VISIBLE
                        }
                        Status.ERROR -> {
                            binding.setListProgressBar.visibility = View.GONE
                            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            binding.setListToolbar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.createSetMenuItem -> {
                        Timber.i(" On check meny clicked item clicked list is ${setListAdapter.itemClickedList.toString()}")
                        if(!setListAdapter.itemClickedList.isEmpty()){
                            viewModel.addSetsToFolder(setListAdapter.itemClickedList, setListArgs.folderId)
                        }else{
                            findNavController().navigate(R.id.homeFragment)
                        }
                        true
                    }
                    else -> false
                }
            }
        }else{
            Timber.i(setListArgs.addSetToFolderFlag.toString())
            setListAdapter = SetListAdapter(SetListListener {
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






}