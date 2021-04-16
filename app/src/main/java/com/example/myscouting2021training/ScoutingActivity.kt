package com.example.myscouting2021training

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
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
            updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
            //LongClickListeners are not void functions, meaning they need a boolean at the end to set off their availability
            return@setOnLongClickListener true
        }
        btn_placement_two.setOnLongClickListener{
            match.elementTwoCount--
            updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
            return@setOnLongClickListener true
        }
    }

    public fun placementOneOnClick(view: View) {
        match.elementOneCount++
        updateButtonLabel(btn_placement_one, R.string.btn_placement_one, match.elementOneCount)
    }

    public fun placementTwoOnClick(view: View) {
        match.elementTwoCount++
        updateButtonLabel(btn_placement_two, R.string.btn_placement_two, match.elementTwoCount)
    }

    public fun incapOnClick(view: View) {
    }

    public fun submitOnClick(view: View) {
    }

    public fun updateButtonLabel(tv: Button, resource: Int, value: Int) {
        tv.text = getString(resource, value)
    }
}