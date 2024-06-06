package com.example.circles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.circles.databinding.FragmentCirclesBinding
import com.example.loggerMod.FragmentExtensionLogger
import com.example.loggerMod.LoggerMod
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt


class CirclesFragment : FragmentExtensionLogger() {
    private lateinit var binding: FragmentCirclesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        LoggerMod.i(TAG, "Fragment onCreateView")

        binding = FragmentCirclesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoggerMod.i(TAG, "Fragment onViewCreated")

        binding.btnCalculate.setOnClickListener {
            if(checkFieldsOk()) {
                LoggerMod.i(TAG, "Fragment: btnCalculate.setOnClickListener")
                calculateIntersection()
            }
        }
    }

    private fun calculateIntersection() {
        val x1: Float = binding.circle1XEditTextNumSign.text.toString().toFloat()
        val y1: Float = binding.circle1YEditTextNumSign.text.toString().toFloat()
        val r1: Float = binding.circle1REditTextNumSign.text.toString().toFloat()
        val x2: Float = binding.circle2XEditTextNumSign.text.toString().toFloat()
        val y2: Float = binding.circle2YEditTextNumSign.text.toString().toFloat()
        val r2: Float = binding.circle2REditTextNumSign.text.toString().toFloat()

        val d: Float = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
        val result = if(d == 0.0f && r1 == r2) {
            "Result: the circles coincide"
        } else if(d > r1 + r2) {
            "Result: the circles do not intersect"
        } else if(d < abs(r1 - r2)) {
            "Result: the circle inside another circle"
        } else if(d == r1 + r2 || d == abs(r1 - r2)) {
            "Result: the circles touch"
        } else {
            "Result: the circles intersect"
        }
        binding.resultTextView.text = result
    }

    private fun checkFieldsOk(): Boolean {
        return binding.circle1XEditTextNumSign.text.isNotEmpty() &&
        binding.circle1YEditTextNumSign.text.isNotEmpty() &&
        binding.circle1REditTextNumSign.text.isNotEmpty() &&
        binding.circle2XEditTextNumSign.text.isNotEmpty() &&
        binding.circle2YEditTextNumSign.text.isNotEmpty() &&
        binding.circle2REditTextNumSign.text.isNotEmpty()
    }


}