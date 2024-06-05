package com.example.circles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circles.databinding.FragmentCirclesBinding

//import com.example.circles.databinding.FragmentCirclesBinding


class CirclesFragment : Fragment() {
    private lateinit var binding: FragmentCirclesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCirclesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    //        MAIN.setTitleAndArrowVisibility("FREE PLAY", true)
    }


}