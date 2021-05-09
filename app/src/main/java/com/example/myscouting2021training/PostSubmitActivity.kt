package com.example.myscouting2021training

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class PostSubmitActivity: Activity() {
    lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_submit_activity)
    }

    private fun retrieveMatchData(){
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
        Log.e("match", match.timeline.timelineList.toString())
    }
}
