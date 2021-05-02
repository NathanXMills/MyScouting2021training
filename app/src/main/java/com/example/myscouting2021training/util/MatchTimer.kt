package com.example.myscouting2021training.util

import android.graphics.Color
import android.os.CountDownTimer
import android.widget.TextView

class MatchTimer (millisInFuture: Long, private val tv: TextView)
    : CountDownTimer(millisInFuture, 1000){

    var timeMS: Long? = null

    @Override
    override fun onTick(millisUntilFinished: Long) {
        timeMS = millisUntilFinished
        tv.text = convMillis(millisUntilFinished).toString()
    }
    override fun onFinish() {
        tv.setTextColor(Color.RED)
        tv.text = "Done"
    }
    fun getTimeMs(): Long {
        timeMS?.let {
            return it
        }
        return 0
    }
}

private fun convMillis(millis: Long): Long {
    return millis / 1000
}