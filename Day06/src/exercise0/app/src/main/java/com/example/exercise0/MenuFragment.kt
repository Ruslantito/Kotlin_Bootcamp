package com.example.exercise0

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise0.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToCircles.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_circlesFragment)
            MAIN.setTitleAndArrowVisibility("CIRCLES", true)
        }
        binding.btnToPrimeNumber.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_primenumbersFragment)
            MAIN.setTitleAndArrowVisibility("PRIME NUMBER", true)
        }
        binding.btnToThermohydrometer.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_thermohydrometerFragment)
            MAIN.setTitleAndArrowVisibility("THERMOHYDROMETER", true)
        }
    }

}