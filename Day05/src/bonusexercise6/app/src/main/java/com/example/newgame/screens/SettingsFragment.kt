package com.example.newgame.screens

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newgame.R
import com.example.newgame.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val prefKeySound = "sound"
    private val prefKeyDelay = "delay"
    private val prefKeyHighlight = "highlight"
    private val prefKeySoundThemes = "soundThemes"
    var seekbarValue = 100
    private val animalsStr = "animals"
    private val humansStr = "humans"
    private val effectsStr = "effects"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MAIN.setTitleAndArrowVisibility("SETTINGS", true)

        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
        setSettingByDefault()

        binding.soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean(prefKeySound, isChecked).apply()
        }

        binding.highlightSwitch.setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean(prefKeyHighlight, isChecked).apply()
        }

        binding.seekBar.setOnSeekBarChangeListener(
            object: OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    seekbarValue = (progress + 1) * 100
                    binding.seekbarTextview.text = seekbarValue.toString()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    editor.putInt(prefKeyDelay, seekbarValue).apply()
                }
            }
        )

        binding.soundsRadioGroup.setOnCheckedChangeListener { _, id ->
            when(id) {
                R.id.radio_animals -> editor.putString(prefKeySoundThemes, animalsStr).apply()
                R.id.radio_humans  -> editor.putString(prefKeySoundThemes, humansStr).apply()
                R.id.radio_effects -> editor.putString(prefKeySoundThemes, effectsStr).apply()
            }
        }
    }

    private fun setSettingByDefault() {
        binding.soundSwitch.isChecked = pref.getBoolean(prefKeySound, true)
        binding.highlightSwitch.isChecked = pref.getBoolean(prefKeyHighlight, true)

        seekbarValue = pref.getInt(prefKeyDelay, 100)
        binding.seekbarTextview.text = seekbarValue.toString()
        binding.seekBar.progress = (seekbarValue / 100) - 1

        setSoundThemes()
    }

    private fun setSoundThemes() {
        val soundThemes = pref.getString(prefKeySoundThemes, animalsStr)
        when(soundThemes) {
            animalsStr -> binding.radioAnimals.isChecked = true
            humansStr  -> binding.radioHumans.isChecked  = true
            effectsStr -> binding.radioEffects.isChecked = true
        }
    }
}
