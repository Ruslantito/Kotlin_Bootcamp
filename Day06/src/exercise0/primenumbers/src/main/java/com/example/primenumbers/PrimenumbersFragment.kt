package com.example.primenumbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.primenumbers.databinding.FragmentPrimenumbersBinding

class PrimenumbersFragment : Fragment() {
    private lateinit var binding: FragmentPrimenumbersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrimenumbersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        MAIN.setTitleAndArrowVisibility("FREE PLAY", true)
    }



}