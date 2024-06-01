package com.example.newgame

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var levelValueTextView: TextView? = null
    private var recordValueTextView: TextView? = null
    private var btn1: androidx.appcompat.widget.AppCompatButton? = null
    private var btn2: androidx.appcompat.widget.AppCompatButton? = null
    private var btn3: androidx.appcompat.widget.AppCompatButton? = null
    private var btn4: androidx.appcompat.widget.AppCompatButton? = null
    private var btnStart: androidx.appcompat.widget.AppCompatButton? = null
    private var levelValue: Int = 0
    private var recordValue: Int = 0
    private var userTurn: Boolean = false
    private var questionList: MutableList<Int> = mutableListOf()
    private var answerList: MutableList<Int> = mutableListOf()
    private var sounds: List<MediaPlayer> = listOf()

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val prefKey = "record"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        levelValueTextView = findViewById(R.id.levelValueTextView)
        recordValueTextView = findViewById(R.id.recordValueTextView)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btnStart = findViewById(R.id.btnStart)

        btn1?.setOnClickListener(this)
        btn2?.setOnClickListener(this)
        btn3?.setOnClickListener(this)
        btn4?.setOnClickListener(this)
        btnStart?.setOnClickListener(this)

        sounds = listOf(
            MediaPlayer.create(this, R.raw.sound1),
            MediaPlayer.create(this, R.raw.sound2),
            MediaPlayer.create(this, R.raw.sound3),
            MediaPlayer.create(this, R.raw.sound4)
        )

        pref = getSharedPreferences("Storage", MODE_PRIVATE)
        editor = pref.edit()
        recordValueTextView?.text = pref.getString(prefKey, "1")

        Thread.sleep(1000)
        btnStart?.performClick()
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
        Thread.sleep(1000)
    }

    private fun answerDataAdd(n: Int) {
        if (userTurn) {
            answerList.add(n)
            checkNewSymb()
        }
    }

    private fun playSound(n: Int) {
        sounds[n-1].start()
    }

//    private fun printToast(str: String) {
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
//    }

    private fun startGame() {
        questionList.clear()
        answerList.clear()
        userTurn = false
        levelValue = 0
        passOneLevel()
    }

    private fun passOneLevel() {
        Thread.sleep(1000)
        levelValueTextView!!.text = levelValue.toString()
        createRandomList()
        lifecycleScope.launch { playList(questionList) }
        ++levelValue
        userTurn = true
    }

    private fun changeButtonsState(state: Boolean) {
        btn1?.isEnabled = state
        btn2?.isEnabled = state
        btn3?.isEnabled = state
        btn4?.isEnabled = state
    }

    private fun checkNewSymb() {
        for (i in 0..< answerList.size) {
            if(answerList.size > questionList.size || answerList[i] != questionList[i]) {
                showScoreDialog(this)
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
        levelValueTextView!!.text = levelValue.toString()
        recordValueTextView!!.text = recordValue.toString()
    }

    private fun createRandomList() {
        questionList.add(Random.nextInt(1, 5)) //(1..4).random())
    }

    private suspend fun playList(list: List<Int>) {
        changeButtonsState(false)
        delay(1000)
        for(item in list) {
            val btn: androidx.appcompat.widget.AppCompatButton? = findViewById(Buttons.entries[item-1].v)
            btn?.setBackgroundResource(ButtonsColor.entries[item-1].v)
            playSound(item)
            delay(1000)
            btn?.setBackgroundResource(ButtonsColor.entries[item-1].s)
        }
        changeButtonsState(true)
    }

    private fun showScoreDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
            .setTitle("FAIL!!")
            .setMessage("DO YOU WANT TO TRY AGAIN?")
            .setPositiveButton("TRY AGAIN...") { _, _ ->
                startGame()
            }
            .setCancelable(false)

        val dialog = builder.create()
        dialog.show()
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