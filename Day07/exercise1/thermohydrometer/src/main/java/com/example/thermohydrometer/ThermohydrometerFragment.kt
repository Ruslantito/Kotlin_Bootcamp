package com.example.thermohydrometer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.loggerMod.FragmentExtensionLogger
import com.example.loggerMod.LoggerMod
import com.example.thermohydrometer.databinding.FragmentThermohydrometerBinding
import kotlinx.coroutines.launch

class ThermohydrometerFragment : FragmentExtensionLogger() {
    private lateinit var binding: FragmentThermohydrometerBinding
    private val textResult = StringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LoggerMod.i(TAG, "Fragment onCreateView")
        binding = FragmentThermohydrometerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerMod.i(TAG, "Fragment onViewCreated")
        var outputMode = "celsius"
        var seasonMode = "winter"

        binding.outputModeRadioGroup.setOnCheckedChangeListener { _, id ->
            LoggerMod.i(TAG, "Fragment: outputModeRadioGroup.setOnCheckedChangeListener")
            when(id) {
                R.id.radio_celsius -> outputMode = "Celsius"
                R.id.radio_kelvin -> outputMode = "Kelvin"
                R.id.radio_fahrenheit -> outputMode = "Fahrenheit"
            }
        }

        binding.seasonModeRadioGroup.setOnCheckedChangeListener { _, id ->
            LoggerMod.i(TAG, "Fragment: seasonModeRadioGroup.setOnCheckedChangeListener")
            when(id) {
                R.id.radio_winter -> seasonMode = "Winter"
                R.id.radio_summer -> seasonMode = "Summer"
            }
        }

        binding.btnCalculate.setOnClickListener {
            LoggerMod.i(TAG, "Fragment: btnCalculate.setOnClickListener")
            viewLifecycleOwner.lifecycleScope.launch {
                LoggerMod.i(TAG, "Fragment: lifecycleScope started")
                if (checkFieldsOk()) {
                    LoggerMod.i(TAG, "Fragment: btnCalculate -> checkFieldsOk")
                    process(seasonMode, outputMode)
                    binding.resultTextView.text = textResult
                }
                LoggerMod.i(TAG, "Fragment: lifecycleScope finished")
            }
        }
    }

    private fun process(season: String, unit: String) {
        checkTemperature(binding.enterTempEditTextNumSign.text.toString().toFloat(), unit, season)
        checkHumidity(binding.enterHumidityEditTextNumSign.text.toString().toFloat(), season)
    }

    private fun convertTemperature(temperature: Float, unit: String): Float {
        val kelvin = 273.15f
        val fahrenheit = 32f
        var numb: Float = temperature
        when(unit.lowercase()) {
            "kelvin" -> numb = temperature * kelvin
            "fahrenheit" -> numb = temperature * fahrenheit
//      "celsius" -> numb = temperature
        }
        return numb
    }

    private fun checkTemperature(temperature: Float, unit: String, season: String) {
        var tempFrom = 22
        var tempTo = 25
        if(season.lowercase() == "winter") {
            tempFrom = 20
            tempTo = 22
        }

        textResult.appendLine()
        textResult.append("The temperature is $temperature ˚C")
        textResult.appendLine()
        textResult.append("The comfortable temperature is from $tempFrom to $tempTo ˚C.")
        textResult.appendLine()

        if(temperature < tempFrom)
            textResult.append("Please, make it warmer by ${convertTemperature(tempFrom - temperature, unit)} degrees.")
        else if(temperature > tempTo)
            textResult.append("Please, make it colder by ${convertTemperature(temperature - tempTo, unit)} degrees.")
        else
            textResult.append("The temperature is comfortable")
        textResult.appendLine()
    }

    private fun checkHumidity(humidity: Float, season: String) {
        val humidityFrom = 30
        var humidityTo = 60
        if(season.lowercase() == "winter") humidityTo = 45

        textResult.appendLine()
        textResult.append("The comfortable humidity is from $humidityFrom% to $humidityTo%.")
        textResult.appendLine()

        if(humidity < humidityFrom)
            textResult.append("Please, make it increase by ${humidityFrom - humidity}%.")
        else if(humidity > humidityTo)
            textResult.append("Please, make it decrease by ${humidity - humidityTo}%.")
        else
            textResult.append("The humidity is comfortable")
        textResult.appendLine()
    }

    private fun checkFieldsOk(): Boolean {
        return binding.enterHumidityEditTextNumSign.text.isNotEmpty() &&
                binding.enterTempEditTextNumSign.text.isNotEmpty()
    }
}