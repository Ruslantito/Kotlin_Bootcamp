package com.example.exercise0

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.exercise0.databinding.ActivityMainBinding
import com.example.loggerMod.LoggerMod

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private val tag: String = this::class.simpleName.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        MAIN = this

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            navProcess()
        }
        LoggerMod.i(tag, "Activity onCreate")
        setTitleAndArrowVisibility("MENU", false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    override fun onStart() {
        super.onStart()
        LoggerMod.i(tag, "Activity onStart")
    }

    override fun onRestart() {
        super.onRestart()
        LoggerMod.i(tag, "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        LoggerMod.i(tag, "Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        LoggerMod.i(tag, "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        LoggerMod.i(tag, "Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoggerMod.i(tag, "Activity onDestroy")
    }

    private fun navProcess() {
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
            R.id.speechFragment -> {
                navSpeechToHome()
            }
            R.id.supercalculationsFragment -> {
                navSupercalcToHome()
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
    private fun navSpeechToHome() {
        navController.navigate(R.id.action_speechFragment_to_menuFragment)
        setTitleAndArrowVisibility("MENU", false)
    }
    private fun navSupercalcToHome() {
        navController.navigate(R.id.action_supercalculationsFragment_to_menuFragment)
        setTitleAndArrowVisibility("MENU", false)
    }
    fun setTitleAndArrowVisibility(title: String, visibility: Boolean) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(visibility)
    }
}