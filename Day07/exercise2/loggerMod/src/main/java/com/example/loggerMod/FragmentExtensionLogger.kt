package com.example.loggerMod

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

open class FragmentExtensionLogger : Fragment() {
    val TAG: String = this::class.simpleName.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LoggerMod.i(TAG, "Fragment onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LoggerMod.i(TAG, "Fragment onAttach")
    }

    override fun onStart() {
        super.onStart()
        LoggerMod.i(TAG, "Fragment onStart")
    }

    override fun onResume() {
        super.onResume()
        LoggerMod.i(TAG, "Fragment onResume")
    }

    override fun onStop() {
        LoggerMod.i(TAG, "Fragment onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        LoggerMod.i(TAG, "Fragment onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        LoggerMod.i(TAG, "Fragment onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        LoggerMod.i(TAG, "Fragment onDetach")
        super.onDetach()
    }
}
