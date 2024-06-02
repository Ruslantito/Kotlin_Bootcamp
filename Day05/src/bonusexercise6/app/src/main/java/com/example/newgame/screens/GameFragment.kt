package com.example.newgame.screens

import com.example.newgame.R
import com.example.newgame.databinding.FragmentGameBinding
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameFragment : Fragment(), View.OnClickListener {
    private var levelValue: Int = 0
    private var recordValue: Int = 0
    private var userTurn: Boolean = false
    private var questionList: MutableList<Int> = mutableListOf()
    private var answerList: MutableList<Int> = mutableListOf()
    private var sounds: List<MediaPlayer> = listOf()
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val prefKey = "record"
    private val prefKeySound = "sound"
    private val prefKeyDelay = "delay"
    private val prefKeyHighlight = "highlight"
    private val prefKeySoundThemes = "soundThemes"

    private val animalsStr = "animals"
    private val humansStr = "humans"
    private val effectsStr = "effects"

    private var withSound = true
    private var withHighlight = true
    private var delayValue = 1000
    private var soundThemes = animalsStr

    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MAIN.setTitleAndArrowVisibility("NEW GAME", true)

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btnStart.setOnClickListener(this)

        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
        setSettingByDefault()

        createSoundList()
        setFreePlayMode()
        setHighlightMode()
    }

    private fun setSettingByDefault() {
        binding.recordValueTextView.text = pref.getString(prefKey, "0")
        withSound = pref.getBoolean(prefKeySound, true)
        withHighlight = pref.getBoolean(prefKeyHighlight, true)
        delayValue = pref.getInt(prefKeyDelay, 1000)
        soundThemes = pref.getString(prefKeySoundThemes, animalsStr).toString()
    }

    private fun createSoundList(){
        var btn1 = R.raw.sound1
        var btn2 = R.raw.sound2
        var btn3 = R.raw.sound3
        var btn4 = R.raw.sound4

        when(soundThemes) {
            animalsStr -> {}
            humansStr -> {
                btn1 = R.raw.human_piu
                btn2 = R.raw.human_mult
                btn3 = R.raw.human_derz
                btn4 = R.raw.human_ta_dam
            }
            effectsStr -> {
                btn1 = R.raw.effects_dram
                btn2 = R.raw.effects_guitar
                btn3 = R.raw.effects_signal
                btn4 = R.raw.effects_ta_dam
            }
        }
        sounds = listOf(
            MediaPlayer.create(requireContext(), btn1),
            MediaPlayer.create(requireContext(), btn2),
            MediaPlayer.create(requireContext(), btn3),
            MediaPlayer.create(requireContext(), btn4)
        )
    }

    private fun setHighlightMode() {
        if(!withHighlight) {
            binding.btn1.setBackgroundResource(R.color.button1_1_color)
            binding.btn2.setBackgroundResource(R.color.button2_1_color)
            binding.btn3.setBackgroundResource(R.color.button3_1_color)
            binding.btn4.setBackgroundResource(R.color.button4_1_color)
        }
    }

    private fun setFreePlayMode() {
        var newState = false
        if(MAIN.navController.currentDestination?.id == R.id.freeGameFragment) {
            newState = true
        }
        changeButtonsState(newState)
        binding.levelTextView.isVisible = !newState
        binding.levelValueTextView.isVisible = !newState
        binding.recordTextView.isVisible = !newState
        binding.recordValueTextView.isVisible = !newState

        if(!newState) {
            Thread.sleep(1000)
            binding.btnStart.performClick()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                playSound(1)
                answerDataAdd(1)
            }
            R.id.btn2 -> {
                playSound(2)
                answerDataAdd(2)
            }
            R.id.btn3 -> {
                playSound(3)
                answerDataAdd(3)
            }
            R.id.btn4 -> {
                playSound(4)
                answerDataAdd(4)
            }
            R.id.btnStart -> {
                startGame()
            }
            else -> {}
        }
        Thread.sleep(delayValue.toLong())
    }

    private fun answerDataAdd(n: Int) {
        if (userTurn) {
            answerList.add(n)
            checkNewSymbol()
        }
    }

    private fun playSound(n: Int) {
        if(withSound) {
            sounds[n - 1].start()
        }
    }

    private fun startGame() {
        questionList.clear()
        answerList.clear()
        userTurn = false
        levelValue = 0
        passOneLevel()
    }

    private fun passOneLevel() {
        Thread.sleep(1000)
        binding.levelValueTextView.text = levelValue.toString()
        createRandomList()
        lifecycleScope.launch { playList(questionList) }
        ++levelValue
        userTurn = true
    }

    private fun changeButtonsState(state: Boolean) {
        binding.btn1.isEnabled = state
        binding.btn2.isEnabled = state
        binding.btn3.isEnabled = state
        binding.btn4.isEnabled = state
    }

    private fun checkNewSymbol() {
        for (i in 0..< answerList.size) {
            if(answerList.size > questionList.size || answerList[i] != questionList[i]) {
                showScoreDialog(requireContext())
                userTurn = false
                changeButtonsState(false)
                updateData()
                return
            }
        }
        if(answerList.size == questionList.size) {
            userTurn = false
            changeButtonsState(false)
            answerList = mutableListOf()
            passOneLevel()
        }
    }

    private fun updateData() {
        if(levelValue > 0) levelValue--
        if(recordValue < levelValue) recordValue = levelValue
        if (recordValue > pref.getString(prefKey, "1")!!.toInt()) {
            editor.putString(prefKey, recordValue.toString()).apply()
        }
        binding.levelValueTextView.text = levelValue.toString()
        binding.recordValueTextView.text = recordValue.toString()
    }

    private fun createRandomList() {
        questionList.add(Random.nextInt(1, 5)) //(1..4).random())
    }

    private suspend fun playList(list: List<Int>) {
        changeButtonsState(false)
        delay(1000)
        for(item in list) {
            val btn: androidx.appcompat.widget.AppCompatButton? = MAIN.findViewById(Buttons.entries[item-1].v)
            if(withHighlight)
                btn?.setBackgroundResource(ButtonsColor.entries[item-1].v)

            playSound(item)
            delay(delayValue.toLong())

            if(withHighlight)
                btn?.setBackgroundResource(ButtonsColor.entries[item-1].s)
        }
        changeButtonsState(true)
    }

    private fun showScoreDialog(context: Context) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("FAIL!!")
            .setMessage("DO YOU WANT TO TRY AGAIN?")
            .setPositiveButton("TRY AGAIN...") { _, _ ->
                startGame()
            }.setNegativeButton("Menu") { _, _ ->
                MAIN.navGameToHome()
            }.setCancelable(false)
        dialog.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        sounds.forEach{it.release()}
    }

    enum class Buttons(val v: Int) {
        BUTTON1(R.id.btn1),
        BUTTON2(R.id.btn2),
        BUTTON3(R.id.btn3),
        BUTTON4(R.id.btn4),
    }

    enum class ButtonsColor(val v: Int, val s: Int) {
        BUTTON1(R.color.red, R.color.button1_color),
        BUTTON2(R.color.orange, R.color.button2_color),
        BUTTON3(R.color.blue, R.color.button3_color),
        BUTTON4(R.color.green, R.color.button4_color),
    }
}