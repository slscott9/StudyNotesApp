package com.example.studynotesapp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.studynotesapp.R
import com.example.studynotesapp.databinding.FragmentHomeLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_login.*
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class HomeLoginFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignUp.setOnClickListener {
            findNavController().navigate(HomeLoginFragmentDirections.actionHomeLoginFragmentToRegisterFragment())
        }
        tvLogin.setOnClickListener {
            findNavController().navigate(HomeLoginFragmentDirections.actionHomeLoginFragmentToLoginFragment())
        }
    }


}

