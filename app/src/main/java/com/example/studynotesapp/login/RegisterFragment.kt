package com.example.studynotesapp.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studynotesapp.BaseFragment
import com.example.studynotesapp.R
import com.example.studynotesapp.login.viewmodels.RegisterViewModel
import com.example.studynotesapp.other.Constants
import com.example.studynotesapp.other.Constants.KEY_LOGGED_IN_EMAIL
import com.example.studynotesapp.other.Constants.KEY_LOGGED_IN_USERNAME
import com.example.studynotesapp.other.Constants.KEY_PASSWORD
import com.example.studynotesapp.other.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private  val viewModel : RegisterViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        viewModel.registerStatus.observe(viewLifecycleOwner) {
            it?.let {
                when(it.status){

                    Status.SUCCESS -> {

                        Timber.i( "User edit text email is ${etRegisterEmail.text.toString()}")
                        Timber.i("User edit text username is ${etRegisterUsername.text.toString()}")
                        Timber.i(" User edit text password is ${etRegisterPassword.text.toString()}")
                        sharedPreferences.edit().putString(KEY_LOGGED_IN_EMAIL, etRegisterEmail.text.toString()).apply()
                        sharedPreferences.edit().putString(KEY_LOGGED_IN_USERNAME, etRegisterUsername.text.toString()).apply()
                        sharedPreferences.edit().putString(KEY_PASSWORD, etRegisterPassword.text.toString()).apply()

                        val spEmail = sharedPreferences.getString(KEY_LOGGED_IN_EMAIL, etRegisterEmail.text.toString())
                        val spUserName = sharedPreferences.getString(KEY_LOGGED_IN_USERNAME, etRegisterUsername.text.toString())
                        val spPassword = sharedPreferences.getString(KEY_PASSWORD, etRegisterPassword.text.toString())


                        Timber.i("share pref email is ${spEmail}")
                        Timber.i(" shared pref username is $spUserName")
                        Timber.i("shared pref password is $spPassword")


                        showSnackBar(it.message ?: "Successfully registered!")

                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                    }

                    Status.ERROR -> {
                        showSnackBar(it.message ?: "An Unknown error occurred")
                    }

                    Status.LOADING -> {
                        registerProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }


    private fun setupListeners() {
        registerToolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.homeLoginFragment)
        }

        btnSignUp.setOnClickListener {
            viewModel.register(etRegisterEmail.text.toString(), etRegisterUsername.text.toString(), etRegisterPassword.text.toString())
        }
    }


}