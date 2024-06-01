package com.example.newgame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newgame.R
import com.example.newgame.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
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

        binding.btnToGame.setOnClickListener {
            MAIN.navController.navigate(R.id.action_homeFragment_to_gameFragment)
        }
        binding.btnToFreeGame.setOnClickListener {
            MAIN.navController.navigate(R.id.action_homeFragment_to_freeGameFragment)
        }
    }
}