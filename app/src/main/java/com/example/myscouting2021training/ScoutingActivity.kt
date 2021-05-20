package com.example.myscouting2021training

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.myscouting2021training.util.MatchTimer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.match_input_activity.*
import kotlinx.android.synthetic.main.scouting_activity.*
import kotlinx.android.synthetic.main.scouting_activity.tv_match_number
import kotlinx.android.synthetic.main.scouting_activity.tv_team_number

class ScoutingActivity : Activity() {
    lateinit var match: Match
    lateinit var matchTimer : MatchTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scouting_activity)
        retrieveMatchData()
        initScoutingSetup()
        matchTimer = MatchTimer(5000, tv_timer_display)
        matchTimer.start()
        match.timeline = Timeline(match.teamNumber)
    }

    private fun retrieveMatchData() {
        match = Gson().fromJson(intent.extras!!.get(match_tag).toString(), Match::class.java)
    }

    private fun initScoutingSetup() {
        tv_match_number.text = match.matchNumber
        tv_team_number.text = match.teamNumber

        btn_placement_one.text = getString(R.string.btn_placement_one, match.elementOneCount.toString())
        btn_placement_two.text = getString(R.string.btn_placement_two, match.elementTwoCount.toString())

        btn_placement_one.setOnLongClickListener{
            //this listener will follow the same formate as the PlacementOnClick functions bellow.
            match.elementOneCount--
            match.timeline.add(ActionType.RemovePlacementOne, matchTimer.getTimeMs(), match.elementOneCount)
            updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
            //LongClickListeners are not void functions, meaning they need a boolean at the end to set off their availability
            return@setOnLongClickListener true
        }
        btn_placement_two.setOnLongClickListener{
            match.elementTwoCount--
            match.timeline.add(ActionType.RemovePlacementTwo, matchTimer.getTimeMs(), match.elementTwoCount)
            updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
            return@setOnLongClickListener true
        }
    }

    public fun placementOneOnClick(view: View) {
        match.elementOneCount++
        match.timeline.add(ActionType.PlacementOne, matchTimer.getTimeMs(), match.elementOneCount)
        updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
    }

    public fun placementTwoOnClick(view: View) {
        match.elementTwoCount++
        match.timeline.add(ActionType.PlacementTwo, matchTimer.getTimeMs(), match.elementTwoCount)
        updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
    }

    public fun incapOnClick(view: View) {
        when(match.isIncap) {
            true -> match.isIncap = false
            false -> match.isIncap = true
        }
        updateButtonBackground(match.isIncap, btn_INCAP)
        match.timeline.add(ActionType.Incap, matchTimer.getTimeMs(), (if (match.isIncap) 1 else 0))
    }

    public fun submitOnClick(view: View) {
        Log.e("Team number:", match.teamNumber)
        Log.e("Timeline list", match.timeline.timelineList.toString())
        intent = Intent( this, PostSubmitActivity::class.java)
        if (matchTimer.isFinished ){
            intent.putExtra(match_tag, Gson().toJson(match))
            startActivity(intent)
        }
    }

    public fun updateButtonLabel(tv: Button, resource: Int, value: Int) {
        tv.text = getString(resource, value)
    }

    public fun updateButtonBackground(value:Boolean, btn: Button) {
        when(value) {
            true ->
                btn.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            false ->
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.btn_standard_white_border))
        }
    }
}