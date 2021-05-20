package com.example.myscouting2021training

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.post_submit_activity.*

class PostSubmitActivity: Activity() {
    lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_submit_activity)
        retrieveMatchData()
        initSetup()
    }

    private fun retrieveMatchData(){
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
    }

    private fun initSetup(){
        match_number_final_display.text = match.matchNumber
        team_number_final_display.text = match.teamNumber
        gameElementOne_final_display.text = match.elementOneCount.toString()
        gameElementTwo_final_display.text = match.elementTwoCount.toString()

        if(match.isIncap == true){
            incap_final_display.text = "Is incap"
        }
        else{
            incap_final_display.text = "Is not incap"
        }
    }
    public fun UpdateOnClick(view: View){
        if (match_number_final_display_edit.text.isNotEmpty()){
            match_number_final_display.text = match_number_final_display_edit.text
            match.matchNumber = match_number_final_display_edit.text.toString()
        }

        if (team_number_final_display_edit.text.isNotEmpty()){
            team_number_final_display.text = team_number_final_display_edit.text
            match.teamNumber = team_number_final_display_edit.text.toString()
        }
    }

    public fun SubmitOnClick(view: View){
        intent = Intent( this, MatchInputActivity::class.java)
        intent.putExtra(match_tag, Gson().toJson(match))
        startActivity(intent)
    }
}
