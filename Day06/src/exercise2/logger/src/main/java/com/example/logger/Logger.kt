package com.example.logger

import android.util.Log
object Logger {
    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }
}
/*
    Log.e() - ошибки (error)
    Log.w() - предупреждения (warning)
    Log.i() - информация (info)
    Log.d() - отладка (degub)
    Log.v() - подробности (verbose)
    Log.wtf() - очень серьёзная ошибка! Работает начиная с Android 2.2
    Log.meow() - Недокументированный метод, используйте на свой страх и риск. Работает не на всех устройствах
 */