package com.example.studynotesapp.ui.home

import android.content.SharedPreferences
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
import com.example.studynotesapp.data.domain.asDomainSetList
import com.example.studynotesapp.databinding.FragmentHomeBinding
import com.example.studynotesapp.other.Constants
import com.example.studynotesapp.other.Constants.KEY_LOGGED_IN_EMAIL
import com.example.studynotesapp.other.Constants.KEY_PASSWORD
import com.example.studynotesapp.other.Constants.NO_EMAIL
import com.example.studynotesapp.other.Constants.NO_PASSWORD
import com.example.studynotesapp.ui.adapters.FolderListAdapter
import com.example.studynotesapp.ui.adapters.SetListAdapter
import com.example.studynotesapp.ui.adapters.SetListListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var folderListAdapter: FolderListAdapter
    private lateinit var setListAdapter: SetListAdapter
    private val viewModel: HomeFragmentViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var currentEmail: String ? = null
    private var currentPassword: String ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!isLoggedIn()){
            findNavController().navigate(R.id.login_nav_graph)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupAdapters()
        setupObservers()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews(){
        binding.rvSets.apply {
            adapter = setListAdapter
        }

        binding.rvFolderList.apply {
            adapter = folderListAdapter
        }
    }

    private fun setupObservers() {
        viewModel.setList.observe(viewLifecycleOwner){
            it?.let {
                setListAdapter.submitList(it.asDomainSetList())
            }
        }
        viewModel.folderList.observe(viewLifecycleOwner){
            it?.let {
                folderListAdapter.submitList(it.asDomainFolderList())
            }
        }
    }

    private fun setupListeners() {
        binding.tvViewAllSets.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetListFragment(addSetToFolderFlag = false))
        }

        binding.tvViewAllFolders.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFolderListFragment())
        }
    }

    private fun setupAdapters() {
        folderListAdapter = FolderListAdapter(FolderListAdapter.FolderListListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFolderDetailFragment(it))
        })
        setListAdapter = SetListAdapter(SetListListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetDetailFragment(it))
        })

    }



    private fun isLoggedIn() : Boolean{
        //default value NO_EMAIL AND NO_PASSWORD
        //use return statement to check if user is logged in
        currentEmail = sharedPreferences.getString(
                KEY_LOGGED_IN_EMAIL,
                NO_EMAIL
        ) ?: NO_EMAIL
        currentPassword = sharedPreferences.getString(
                KEY_PASSWORD,
                NO_PASSWORD
        ) ?: NO_PASSWORD

        Timber.i("In Homefragment isLoggedIn function shared pref email is $currentEmail, shared pref password $currentPassword")

        return currentEmail != NO_EMAIL && currentPassword != NO_PASSWORD
    }


}