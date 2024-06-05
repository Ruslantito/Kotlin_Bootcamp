package com.example.exercise0

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.exercise0.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        MAIN = this



        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        setTitleAndArrowVisibility("MENU", false)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            when(navController.currentDestination?.id) {
                R.id.circlesFragment -> {
                    navCirclesToHome()
                }
                R.id.primenumbersFragment -> {
                    navPrimenumbersToHome()
                }
                R.id.thermohydrometerFragment -> {
                    navThermohydrometerToHome()
                }
            }
        }

    }

    private fun navCirclesToHome() {
        navController.navigate(R.id.action_circlesFragment_to_menuFragment)
        setTitleAndArrowVisibility("MENU", false)
    }
    private fun navPrimenumbersToHome() {
        navController.navigate(R.id.action_primenumbersFragment_to_menuFragment)
        setTitleAndArrowVisibility("MENU", false)
    }
    private fun navThermohydrometerToHome() {
        navController.navigate(R.id.action_thermohydrometerFragment_to_menuFragment)
        setTitleAndArrowVisibility("MENU", false)
    }
    fun setTitleAndArrowVisibility(title: String, visibility: Boolean) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }


}