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
import com.example.newgame.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val prefKeySound = "sound"
    private val prefKeyDelay = "delay"
    private val prefKeyHighlight = "highlight"
    var seekbarValue = 100

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
    }

    private fun setSettingByDefault() {
        binding.soundSwitch.isChecked = pref.getBoolean(prefKeySound, true)
        binding.highlightSwitch.isChecked = pref.getBoolean(prefKeyHighlight, true)

        seekbarValue = pref.getInt(prefKeyDelay, 100)
        binding.seekbarTextview.text = seekbarValue.toString()
        binding.seekBar.progress = (seekbarValue / 100) - 1
    }
}
