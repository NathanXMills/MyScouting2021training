package com.example.myscouting2021training

data class Timeline (private var team: String){
    var timelineList: MutableList<TimelineEntry> = mutableListOf()

    fun getTeam(): String{
        return team
    }

    fun getTimelineEntry(index:Int): TimelineEntry {
        return timelineList.get(index)
    }

    fun add(actionType: ActionType, time: Long, concurrentValue: Int){
        timelineList.add(TimelineEntry(actionType, time, concurrentValue))
    }
    
    data class TimelineEntry(
        private var actionType: ActionType,
        private var time: Long,
        private var concurrentValue: Int){

        fun getActionType(): ActionType {
            return actionType
        }

        fun getTime(): Long {
            return time
        }

        fun getConcurrentValue(): Int {
            return concurrentValue
        }
    }

    enum class ActionType {
        Placement,
        Incap
    }
}
