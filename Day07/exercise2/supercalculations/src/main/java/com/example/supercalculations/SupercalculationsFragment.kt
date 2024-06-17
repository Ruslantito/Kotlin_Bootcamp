package com.example.supercalculations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.loggerMod.FragmentExtensionLogger
import com.example.loggerMod.LoggerMod
import com.example.supercalculations.databinding.FragmentSupercalculationsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt


class SupercalculationsFragment : FragmentExtensionLogger(), View.OnClickListener  {
    private lateinit var binding: FragmentSupercalculationsBinding
    private var number: Int = 0
    private val jobMap: MutableMap<String, Job?> = mutableMapOf()

    enum class JOB(val v: String) {
        FACT("Factorial"),
        SQRT("Sqrt"),
        LG("Logarithm"),
        POW("Pow"),
        PRIME("Prime"),
        ALL("All"),
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LoggerMod.i(TAG, "Fragment onCreateView")
        binding = FragmentSupercalculationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerMod.i(TAG, "Fragment onViewCreated")

        jobMap[JOB.FACT.v] = null
        jobMap[JOB.SQRT.v] = null
        jobMap[JOB.LG.v] = null
        jobMap[JOB.POW.v] = null
        jobMap[JOB.PRIME.v] = null
        jobMap[JOB.ALL.v] = null

        binding.btnRunall.setOnClickListener(this)
        binding.btnFactorial.setOnClickListener(this)
        binding.btnSqrt.setOnClickListener(this)
        binding.btnLogarithms.setOnClickListener(this)
        binding.btnPow.setOnClickListener(this)
        binding.btnPrime.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val runCalc = checkFieldsOk()
        if (runCalc) {
            number = binding.numberEditTextNumSign.text.toString().toInt()
        }
        when(p0?.id) {
            R.id.btn_factorial -> {
                LoggerMod.i("FACTORIAL", "btn_factorial start")
                if (runCalc) {
                    runProgress(binding.btnFactorial, JOB.FACT.v)
                }
                LoggerMod.i("FACTORIAL", "btn_factorial finish")
            }
            R.id.btn_sqrt -> {
                LoggerMod.i("SQRT", "btn_sqrt start")
                if (runCalc) {
                    runProgress(binding.btnSqrt, JOB.SQRT.v)
                }
                LoggerMod.i("SQRT", "btn_sqrt finish")
            }
            R.id.btn_logarithms -> {
                LoggerMod.i("LOGARITHM", "btn_logarithms start")
                if (runCalc) {
                    runProgress(binding.btnLogarithms, JOB.LG.v)
                }
                LoggerMod.i("LOGARITHM", "btn_logarithms finish")
            }
            R.id.btn_pow -> {
                LoggerMod.i("POW", "btn_pow start")
                if (runCalc) {
                    runProgress(binding.btnPow, JOB.POW.v)
                }
                LoggerMod.i("POW", "btn_pow finish")
            }
            R.id.btn_prime -> {
                LoggerMod.i("PRIME", "btn_prime start")
                if (runCalc) {
                    runProgress(binding.btnPrime, JOB.PRIME.v)
                }
                LoggerMod.i("PRIME", "btn_prime finish")
            }
            R.id.btn_runall -> {
                LoggerMod.i("RUNALL", "btnRunall start")
                if(runCalc) {
                    runProgress(binding.btnRunall, JOB.ALL.v)
                }
                LoggerMod.i("RUNALL", "btnRunall finish")
            }
        }
    }

    private fun runProgress(btn: Button, jobName: String) {
        LoggerMod.i("ALL", "--== btn: {${btn.text}} | jobName: $jobName" )
        when(btn.text) {
            getString(R.string.run) -> {
                buttonThemesChange(btn, true)
                val coroutineProgress = CoroutineScope(Dispatchers.Main)
                jobMap[jobName] = coroutineProgress.launch {
                    when(jobName) {
                        JOB.FACT.v -> {
                            coroutineFact()
                        }
                        JOB.SQRT.v -> {
                            coroutineSqrt()
                        }
                        JOB.LG.v -> {
                            coroutineLg()
                        }
                        JOB.POW.v -> {
                            coroutinePow()
                        }
                        JOB.PRIME.v -> {
                            coroutinePrime()
                        }
                        JOB.ALL.v -> {
                            coroutineAll()
                        }
                    }
                    buttonThemesChange(btn, false)
                }
            }
            getString(R.string.cancel) -> {
                val coroutineFact = CoroutineScope(Dispatchers.Default)
                coroutineFact.launch {
                    jobMap[jobName]?.cancelAndJoin()
                }
                buttonThemesChange(btn, false)
            }
        }
    }

    private fun buttonThemesChange(btn: Button, theme1: Boolean) {
        if(theme1) {
            btn.text = getString(R.string.cancel)
            btn.setBackgroundResource(R.drawable.button_cancel)
        } else {
            btn.text = getString(R.string.run)
            btn.setBackgroundResource(R.drawable.button)
        }
    }

    private suspend fun coroutineFact() {
        LoggerMod.i("FACTORIAL", "coroutineFact start")
        coroutineScope {
//            delay(5000)
            val run1 = async(Dispatchers.Default) {
                factorial()
            }
            binding.factorialTextView.text = run1.await()
        }
        LoggerMod.i("FACTORIAL", "coroutineFact finish")
    }
    private suspend fun coroutineSqrt() {
        LoggerMod.i("SQRT", "coroutineSqrt start")
        coroutineScope {
            val run1 = async(Dispatchers.Default) {
                squareRoot()
            }
            val run2 = async(Dispatchers.Default) {
                cubeRoot()
            }
            binding.sqrt2TextView.text = run1.await()
            binding.sqrt3TextView.text = run2.await()
        }
        LoggerMod.i("SQRT", "coroutineSqrt finish")
    }
    private suspend fun coroutineLg() {
        LoggerMod.i("LG", "coroutineLg start")
        coroutineScope {
            val run1 = async(Dispatchers.Default) {
                logarithmLog10()
            }
            val run2 = async(Dispatchers.Default) {
                logarithmLn()
            }
            binding.logTextView.text = run1.await()
            binding.lnTextView.text = run2.await()
        }
        LoggerMod.i("LG", "coroutineLg finish")
    }
    private suspend fun coroutinePow() {
        LoggerMod.i("POW", "coroutinePow start")
        coroutineScope {
            val run1 = async(Dispatchers.Default) {
                pow2()
            }
            val run2 = async(Dispatchers.Default) {
                pow3()
            }
            binding.pow2TextView.text = run1.await()
            binding.pow3TextView.text = run2.await()
        }
        LoggerMod.i("POW", "coroutinePow finish")
    }
    private suspend fun coroutinePrime() {
        LoggerMod.i("PRIME", "coroutinePrime start")
        coroutineScope {
            val run1 = async(Dispatchers.Default) {
                isPrime()
            }
            binding.primeTextView.text = run1.await()
        }
        LoggerMod.i("PRIME", "coroutinePrime finish")
    }
    private suspend fun coroutineAll() {
//        delay(5000)
        LoggerMod.i("ALL", "coroutineALL start")
        coroutineScope {
            val run1 = async(Dispatchers.Default) {
                factorial()
            }
            val run2 = async(Dispatchers.Default) {
                squareRoot()
            }
            val run3 = async(Dispatchers.Default) {
                cubeRoot()
            }
            val run4 = async(Dispatchers.Default) {
                logarithmLog10()
            }
            val run5 = async(Dispatchers.Default) {
                logarithmLn()
            }
            val run6 = async(Dispatchers.Default) {
                pow2()
            }
            val run7 = async(Dispatchers.Default) {
                pow3()
            }
            val run8 = async(Dispatchers.Default) {
                isPrime()
            }
            binding.factorialTextView.text = run1.await()
            binding.sqrt2TextView.text = run2.await()
            binding.sqrt3TextView.text = run3.await()
            binding.logTextView.text = run4.await()
            binding.lnTextView.text = run5.await()
            binding.pow2TextView.text = run6.await()
            binding.pow3TextView.text = run7.await()
            binding.primeTextView.text = run8.await()
        }
        LoggerMod.i("ALL", "coroutineAll finish")
    }

    private fun factorial(): String {
        var fact = BigInteger.ONE
        for (i in 1..number) {
            fact = fact.multiply(i.toBigInteger())
        }
        fact = (Math.round(fact.toDouble() * 100) / 100).toBigInteger()
        return fact.toLong().toString()
    }
    private fun squareRoot(): String {
        return (Math.round(sqrt(number.toDouble()) * 100.0) / 100.0).toString()
    }
    private fun cubeRoot(): String {
        return (Math.round(number.toDouble().pow(1.0/3.0) * 100.0) / 100.0).toString()
    }
    private fun logarithmLog10(): String {
        if(number > 0) return (Math.round(log10(number.toDouble()) * 100.0) / 100.0).toString()
        return log10(number.toDouble()).toString()
    }
    private fun logarithmLn(): String {
        if(number > 0)  return (Math.round(kotlin.math.ln(number.toDouble()) * 100.0) / 100.0).toString()
        return kotlin.math.ln(number.toDouble()).toString()
    }
    private fun pow2(): String {
        return (Math.round(number.toDouble().pow(2.0) * 100.0) / 100.0).toString()
    }
    private fun pow3(): String {
        return (Math.round(number.toDouble().pow(3.0) * 100.0) / 100.0).toString()
    }
    private fun isPrime(): String {
        if (number < 2) return false.toString()
        for (num in 2..sqrt(number.toDouble()).toInt()) {
            if (number % num == 0) return false.toString()
        }
        return true.toString()
    }
    private fun checkFieldsOk(): Boolean {
        return binding.numberEditTextNumSign.text.isNotEmpty()
    }

}