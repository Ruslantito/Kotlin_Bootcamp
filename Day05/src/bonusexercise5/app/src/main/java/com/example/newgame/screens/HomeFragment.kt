package com.example.newgame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newgame.R
import com.example.newgame.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToGame.setOnClickListener(this)
        binding.btnToFreeGame.setOnClickListener(this)
        binding.btnToAbout.setOnClickListener(this)
        binding.btnToSettings.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_toGame -> MAIN.navController.navigate(R.id.action_homeFragment_to_gameFragment)
            R.id.btn_toFreeGame -> MAIN.navController.navigate(R.id.action_homeFragment_to_freeGameFragment)
            R.id.btn_toAbout -> MAIN.navController.navigate(R.id.action_homeFragment_to_aboutFragment)
            R.id.btn_toSettings -> MAIN.navController.navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }
}
