package com.example.newgame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.newgame.databinding.ActivityMainBinding
import com.example.newgame.screens.MAIN


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        MAIN = this

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        setTitleAndArrowVisibility("MENU", false)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            when(navController.currentDestination?.id) {
                R.id.gameFragment -> {
                    navGameToHome()
                }
                R.id.freeGameFragment -> {
                    navFreeGameToHome()
                }
            }
        }
    }

    private fun navGameToHome() {
        navController.navigate(R.id.action_gameFragment_to_homeFragment)
        setTitleAndArrowVisibility("MENU", false)
    }
    private fun navFreeGameToHome() {
        navController.navigate(R.id.action_freeGameFragment_to_homeFragment)
        setTitleAndArrowVisibility("MENU", false)
    }

    fun setTitleAndArrowVisibility(title: String, visibility: Boolean) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }
}