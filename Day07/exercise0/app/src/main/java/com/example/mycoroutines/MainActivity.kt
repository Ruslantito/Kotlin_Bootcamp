package com.example.mycoroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mycoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.i("MainActivity", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFirst.setOnClickListener {
            Log.i("MainActivity", "btnFirst.setOnClickListener")
            first()
        }

        binding.btnSecond.setOnClickListener{
            Log.i("MainActivity", "btnSecond.setOnClickListener")
            second()
        }

        binding.btnThird.setOnClickListener{
            Log.i("MainActivity", "btnThird.setOnClickListener")
            third()
            thirdNext()
        }
    }


    //    coroutine basics,
    //    cancellation and timeouts,
    //    context and dispatchers,

    private fun first() {
        Log.i("FIRST", "Function started")
        runBlocking {
            Log.i("FIRST", "runBlocking started")
            val job = launch {
                delay(2000)
                Log.i("FIRST", "Done something successfully.")
            }
            job.join()
            Log.i("FIRST", "runBlocking finished")
        }
        Log.i("FIRST", "Function finished")
    }

    private fun second() {
        Log.i("SECOND", "Function started")
        lifecycleScope.launch {
            Log.i("SECOND", "lifecycleScope started")
            val status = loadData()
            Log.i("SECOND", "lifecycleScope finished with status: $status")
        }
        Log.i("SECOND", "Function finished")
    }

    private suspend fun loadData(): Boolean {
        Log.i("SECOND", "LoadData started")
        delay(3_000)
        Log.i("SECOND", "LoadData finished")
        return true
    }

    private fun third() {
        runBlocking {
            val job = launch(Dispatchers.Unconfined) {
                repeat(1000) {
                    Log.i("THIRD", "Default  : running in thread ${Thread.currentThread().name}")
                    longTask()
                }
            }
            delay(1500)
            job.cancelAndJoin()
            Log.i("THIRD", "JOB: canceled")
        }
    }

    private suspend fun longTask(){
        Log.i("THIRD", "executing longTask on...: ${Thread.currentThread().name}")
        delay(1000)
        Log.i("THIRD", "longTask ends on thread ...: ${Thread.currentThread().name}")
    }


    private fun thirdNext() {
        Log.i("THIRD_NEXT", "thirdNext started")
        runBlocking {
            launch {
                val result = withTimeoutOrNull(1300L) {
                    Log.i("THIRD_NEXT", "withTimeoutOrNull started")
                    repeat(1000) { i ->
                        Log.i("THIRD_NEXT", "I'm sleeping $i ...")
                        delay(500L)
                    }
                    "Done"
                }
                Log.i("THIRD_NEXT", "Result: $result")
            }
            Log.i("THIRD_NEXT", "thirdNext finished")
        }
    }


}