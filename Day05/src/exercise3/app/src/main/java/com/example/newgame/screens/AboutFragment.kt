package com.example.newgame.screens

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.newgame.R
import com.example.newgame.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding
    private lateinit var pref: SharedPreferences
    private val prefKey = "record"
    private var recordValue: String? = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MAIN.setTitleAndArrowVisibility("ABOUT", true)
        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        recordValue = pref.getString(prefKey, "0")
        binding.aboutRecordTextView.text = getString(R.string.about_record, recordValue)
    }
}