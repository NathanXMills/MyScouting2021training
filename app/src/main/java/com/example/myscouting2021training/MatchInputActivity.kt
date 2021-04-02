package com.example.myscouting2021training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.match_input_activity.*

class MatchInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_input_activity)
    }

    fun getTeamNumber(): String{
        return et_team_number.text.toString()
    }

    fun getMatchNumber(): String{
        return et_match_number.text.toString()
    }
    
    fun startNextActivity(view: View){
        if(checkEditText(et_match_number, et_team_number)) {
            startActivity(Intent( this, ScoutingActivity::class.java))
        }
    }

    fun checkEditText(vararg et: EditText): Boolean {
        for (e in et) {
            if (e.text.isEmpty()) return false
        }
        return true
    }
}