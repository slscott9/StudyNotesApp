package com.example.studynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = Navigation.findNavController(this, R.id.navHostFragment)

        bottom_nav.setupWithNavController(navController)


        Navigation.findNavController(this, R.id.navHostFragment).addOnDestinationChangedListener() { controller, destination, arguments ->
            when(destination.id){

                R.id.loginFragment -> {
                    bottom_nav.visibility = View.GONE
                }

                R.id.homeLoginFragment -> {
                    bottom_nav.visibility = View.GONE
                }

                R.id.registerFragment -> {
                    bottom_nav.visibility = View.GONE
                }

                else -> {
                    bottom_nav.visibility = View.VISIBLE
                }
            }
        }
    }
}