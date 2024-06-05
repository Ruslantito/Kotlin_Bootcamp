package com.example.thermohydrometer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thermohydrometer.databinding.FragmentThermohydrometerBinding

class ThermohydrometerFragment : Fragment() {
    private lateinit var binding: FragmentThermohydrometerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThermohydrometerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        MAIN.setTitleAndArrowVisibility("FREE PLAY", true)
    }


}