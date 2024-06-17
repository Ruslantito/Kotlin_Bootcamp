package com.example.speech

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.loggerMod.FragmentExtensionLogger
import com.example.loggerMod.LoggerMod
import com.example.speech.databinding.FragmentSpeechBinding
import kotlinx.coroutines.launch
import kotlin.math.abs

class SpeechFragment : FragmentExtensionLogger()  {
    private lateinit var binding: FragmentSpeechBinding
    private val limitNumber: Int = 1000000000
    private val numbersToWords: MutableMap<Int, String> = mutableMapOf()
    private val resultStr = StringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LoggerMod.i(TAG, "Fragment onCreateView")
        binding = FragmentSpeechBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerMod.i(TAG, "Fragment onViewCreated")

        binding.btnCalculate.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                LoggerMod.i(TAG, "Fragment: lifecycleScope started")
                resultStr.clear()
                LoggerMod.i(TAG, "Fragment: btnCalculate.setOnClickListener")
                if (checkFieldsOk()) {
                    LoggerMod.i(TAG, "Fragment: btnCalculate -> checkFieldsOk")
                    val numb = binding.numberEditTextNumSign.text.toString().toDouble()
                    if (readNum(numb)) {
                        initNumbersData()
                        convertNumberToWord(numb.toInt())
                        binding.resultTextView.text = resultStr
                    } else {
                        binding.resultTextView.text = getString(R.string.number_out_of_limits)
                    }
                } else {
                    binding.resultTextView.text = getString(R.string.please_enter_the_number)
                }
                LoggerMod.i(TAG, "Fragment: lifecycleScope finished")
            }
        }
    }

    private fun convertNumberToWord(numb: Int) {
        var minus = false
        if(numb < 0) minus = true
        val number = abs(numb)
        if (minus) {
            resultStr.append("minus-")
            resultStr.appendLine()
        }
        processCount(number)
        resultStr.appendLine()
    }

    private fun processCount(number: Int) {
        if(number <= 20) {
            resultStr.append(numbersToWords[number])
        } else if(number in 21..99) {
            val number10 = from21till99(number)
            if (number10 > 0) {
                processCount(number10)
            }
        }
        else {
            var devideNumber = 10
            when (number) {
                in 100..999 -> devideNumber = 100
                in 1000..999999 -> devideNumber = 1000
                in 1000000..999999999 -> devideNumber = 1000000
                1000000000 -> devideNumber = 1000000000
            }
            val number10 = from99tillEnd(number, devideNumber)
            if (number10 > 0) {
                processCount(number10)
            }
        }
    }

    private fun from21till99(number: Int): Int {
        val tmpN1 = (number / 10) * 10
        resultStr.append(numbersToWords[tmpN1])
        val tmpN2 = number % 10
        if(tmpN2 > 0) {
            resultStr.append("-")
        }
        return tmpN2
    }

    private fun from99tillEnd(number: Int, devideNumb: Int): Int {
        val tmpN1 = (number / devideNumb)
        if(tmpN1 > 20) processCount(tmpN1)
        else resultStr.append(numbersToWords[tmpN1])
        resultStr.append("-${numbersToWords[devideNumb]}")
        val tmpN2 = number % devideNumb
        if(tmpN2 > 0) {
            resultStr.append("-")
        }
        return tmpN2
    }

    private fun initNumbersData() {
        numbersToWords[0] = "zero"
        numbersToWords[1] = "one"
        numbersToWords[2] = "two"
        numbersToWords[3] = "three"
        numbersToWords[4] = "four"
        numbersToWords[5] = "five"
        numbersToWords[6] = "six"
        numbersToWords[7] = "seven"
        numbersToWords[8] = "eight"
        numbersToWords[9] = "nine"
        numbersToWords[10] = "ten"
        numbersToWords[11] = "eleven"
        numbersToWords[12] = "twelve"
        numbersToWords[13] = "thirteen"
        numbersToWords[14] = "fourteen"
        numbersToWords[15] = "fifteen"
        numbersToWords[16] = "sixteen"
        numbersToWords[17] = "seventeen"
        numbersToWords[18] = "eighteen"
        numbersToWords[19] = "nineteen"
        numbersToWords[20] = "twenty"

        numbersToWords[30] = "thirty"
        numbersToWords[40] = "forty"
        numbersToWords[50] = "fifty"
        numbersToWords[60] = "sixty"
        numbersToWords[70] = "seventy"
        numbersToWords[80] = "eighty"
        numbersToWords[90] = "ninety"

        numbersToWords[100] = "hundred"
        numbersToWords[1000] = "thousand"
        numbersToWords[1000000] = "million"
        numbersToWords[1000000000] = "billion"
    }

    private fun readNum(numb: Double): Boolean {
        return !(numb < (limitNumber * -1) || numb > limitNumber)
    }

    private fun checkFieldsOk(): Boolean {
        return binding.numberEditTextNumSign.text.isNotEmpty()
    }
}