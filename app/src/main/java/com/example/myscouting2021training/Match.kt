package com.example.myscouting2021training

data class Match (
    var teamNumber: String,
    var matchNumber: String,
    var elementOneCount: Int = 0,
    var elementTwoCount: Int = 0,
    var isIncap: Boolean = false,
    var timeline: Timeline = Timeline(teamNumber)
)