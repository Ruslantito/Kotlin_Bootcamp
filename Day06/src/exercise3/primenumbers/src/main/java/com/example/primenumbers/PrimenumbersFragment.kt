package com.example.primenumbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loggerMod.FragmentExtensionLogger
import com.example.loggerMod.LoggerMod
import com.example.primenumbers.databinding.FragmentPrimenumbersBinding
import kotlin.math.pow

class PrimenumbersFragment : FragmentExtensionLogger() {
    private lateinit var binding: FragmentPrimenumbersBinding
    private var mode: String = "lower"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        LoggerMod.i(TAG, "Fragment onCreateView")

        binding = FragmentPrimenumbersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoggerMod.i(TAG, "Fragment onViewCreated")

        binding.modeRadioGroup.setOnCheckedChangeListener { _, id ->
            LoggerMod.i(TAG, "Fragment: modeRadioGroup.setOnCheckedChangeListener")
            when(id) {
                R.id.radio_lower -> mode = "lower"
                R.id.radio_higher -> mode = "higher"
            }
        }

        binding.btnCalculate.setOnClickListener {
            if(checkFieldsOk()) {
                LoggerMod.i(TAG, "Fragment: btnCalculate.setOnClickListener")
                process(mode)
            }
        }
    }

    private fun process(mode: String) {
        var numb = binding.enterNrEditTextNumSign.text.toString()
        if(mode == "lower") {
            numb = numb.reversed()
        }
        readNum(numb.toInt(), numb.length)
    }

    private fun readNum(numb: Int, length: Int) {
        val textResult = StringBuilder()
        textResult.append("Result:")
        textResult.appendLine()
        val step = 10f
        for (i in length downTo 1) {
            val tmp: Int = (numb / (step).pow(i-1)).toInt()
            if(isPrime(tmp)) textResult.append("$tmp - prime")
            else textResult.append("$tmp")
            textResult.appendLine()
        }
        binding.resultTextView.text = textResult
    }

    private fun isPrime(numb: Int): Boolean {
        var i = 2
        while (i < numb / 2) {
            if (numb % i == 0) return false
            i++
        }
        return true
    }

    private fun checkFieldsOk(): Boolean {
        return binding.enterNrEditTextNumSign.text.isNotEmpty()
    }

}