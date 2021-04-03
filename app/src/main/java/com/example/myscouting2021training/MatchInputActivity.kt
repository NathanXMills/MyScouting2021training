package com.example.myscouting2021training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.gson.Gson
import kotlinx.android.synthetic.main.match_input_activity.*

lateinit var intent:Intent
public var match_tag = "Match"

class MatchInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_input_activity)
    }
    
    fun startNextActivity(view: View){
        intent = Intent( this, ScoutingActivity::class.java)
        if(checkEditText(et_match_number, et_team_number)) {
            intent.putExtra(match_tag, Gson().toJson(Match(getTeamNumber(), getMatchNumber())))
            Log.e("match_data","${Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)}")
            startActivity(intent)
        }
    }

    fun getTeamNumber(): String{
        return et_team_number.text.toString()
    }

    fun getMatchNumber(): String{
        return et_match_number.text.toString()
    }

    fun checkEditText(vararg et: EditText): Boolean {
        for (e in et) {
            if (e.text.isEmpty()) return false
        }
        return true
    }
}