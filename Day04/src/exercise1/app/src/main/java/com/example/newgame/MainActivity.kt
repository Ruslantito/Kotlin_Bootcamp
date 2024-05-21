package com.example.newgame

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
    private var levelIsPassed: Boolean = true
    private var userTurn: Boolean = false
    private var questionList: List<Int> = listOf()
    private var answerList: MutableList<Int> = mutableListOf()


    enum class Sounds(val v: Int) {
        SOUND1(R.raw.sound1),
        SOUND2(R.raw.sound2),
        SOUND3(R.raw.sound3),
        SOUND4(R.raw.sound4),
    }

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
    }

    private fun answerDataAdd(n: Int) {
        if (userTurn) {
            answerList.add(n)
            checkNewSymb()
        }
    }

    private fun playSound(n: Int) {
        Thread.sleep(1000)
        MediaPlayer.create(applicationContext, Sounds.entries[n-1].v).start()
    }

    private fun printToast(str: String) {
        Toast.makeText(this, "Text of button: $str", Toast.LENGTH_SHORT).show()
    }


    private fun startGame() {
        questionList = listOf()
        answerList = mutableListOf()
        userTurn = false

        levelValue = 0
        passOneLevel()
    }

    private fun passOneLevel() {
//        changeButtonsState(false)

        Thread.sleep(1000)
        printToast("---=== NEW LEVEL ===---")
        Thread.sleep(1000)

        levelValueTextView!!.text = levelValue.toString()

        questionList = createRandomList(levelValue + 1)
        playList(questionList)
        ++levelValue

        changeButtonsState(true)
    }

    private fun changeButtonsState(state: Boolean) {
        userTurn = state
        btn1?.isEnabled = state
//        btn1?.isClickable = state
        btn2?.isEnabled = state
//        btn2?.isClickable = state
        btn3?.isEnabled = state
//        btn3?.isClickable = state
        btn4?.isEnabled = state
//        btn4?.isClickable = state
    }

    private fun checkNewSymb() {
        for (i in 0..< answerList.size) {
            if(answerList.size > questionList.size || answerList[i] != questionList[i]) {
                Toast.makeText(this, "FAIL, PLEASE TRY AGAIN!!", Toast.LENGTH_SHORT).show()
                userTurn = false
                updateData()
                return
            }
        }
        if(answerList.size == questionList.size) {
            userTurn = false
            answerList = mutableListOf()

            changeButtonsState(false)
            passOneLevel()
        }
    }


    private fun updateData() {
        if(recordValue < levelValue)
            recordValue = levelValue

        levelValueTextView!!.text = levelValue.toString()
        recordValueTextView!!.text = recordValue.toString()
    }

    private fun createRandomList(size: Int): List<Int> {
        return List(size) { Random.nextInt(1, 5) }
    }

    private fun playList(list: List<Int>) {
        for(item in list) {
            val btn: androidx.appcompat.widget.AppCompatButton? = findViewById(Buttons.entries[item-1].v)

            val thread = Thread {
                printToast("new STATUS!!!")
                btn?.isPressed = true
            }
            Thread.sleep(2000)
            thread.start()

            Thread.sleep(4000)

            btn?.performClick();
            btn?.isPressed = false
            Thread.sleep(1000)
        }
    }

    enum class Buttons(val v: Int) {
        BUTTON1(R.id.btn1),
        BUTTON2(R.id.btn2),
        BUTTON3(R.id.btn3),
        BUTTON4(R.id.btn4),
    }


}