package com.example.myscouting2021training

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.scouting_activity.*

class ScoutingActivity : Activity() {

    lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scouting_activity)
        retrieveMatchData()
        initScoutingSetup()
    }

    private fun retrieveMatchData(){
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
    }

    private fun initScoutingSetup(){
        tv_match_number.text = match.matchNumber
        tv_team_number.text = match.teamNumber
    }
}