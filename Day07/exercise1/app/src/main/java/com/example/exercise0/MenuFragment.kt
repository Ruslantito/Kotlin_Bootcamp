package com.example.exercise0

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise0.databinding.FragmentMenuBinding
import com.example.loggerMod.FragmentExtensionLogger
import com.example.loggerMod.LoggerMod

class MenuFragment : FragmentExtensionLogger(), View.OnClickListener {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LoggerMod.i(TAG, "Fragment onCreateView")
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerMod.i(TAG, "Fragment onViewCreated")
        binding.btnToCircles.setOnClickListener(this)
        binding.btnToPrimeNumber.setOnClickListener(this)
        binding.btnToThermohydrometer.setOnClickListener(this)
        binding.btnToTSpeech.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btn_toCircles -> {
                MAIN.navController.navigate(R.id.action_menuFragment_to_circlesFragment)
                MAIN.setTitleAndArrowVisibility("CIRCLES", true)
            }
            R.id.btn_toPrimeNumber -> {
                MAIN.navController.navigate(R.id.action_menuFragment_to_primenumbersFragment)
                MAIN.setTitleAndArrowVisibility("PRIME NUMBER", true)
            }
            R.id.btn_toThermohydrometer -> {
                MAIN.navController.navigate(R.id.action_menuFragment_to_thermohydrometerFragment)
                MAIN.setTitleAndArrowVisibility("THERMOHYDROMETER", true)
            }
            R.id.btn_toTSpeech -> {
                MAIN.navController.navigate(R.id.action_menuFragment_to_speechFragment)
                MAIN.setTitleAndArrowVisibility("SPEECH", true)
            }
        }
    }
}