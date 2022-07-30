package com.example.medtimev2

import java.util.*

class Record {
    var name: String = ""
    var property: String = ""
    var warning: String = ""
    var countPerTime: Double = 0.0
    var timePerDay: Int = 0
    var direction: Direction = Direction.UNDEFINED
    var directionCalendar: DirectionCalendar? = null

    fun getAlerts(): List<Calendar> {
        if (this.directionCalendar == null) {
            return listOf()
        }

        return when (this.direction) {
            Direction.BEFORE_MEAL, Direction.AFTER_MEAL -> listOf(this.directionCalendar!!.breakfast, this.directionCalendar!!.lunch, this.directionCalendar!!.dinner)
            Direction.BEFORE_BED -> listOf(this.directionCalendar!!.beforeBed)
            Direction.UNDEFINED -> listOf()
        }.filterNotNull()
    }
}

enum class Direction {
    BEFORE_MEAL, AFTER_MEAL, BEFORE_BED, UNDEFINED
}

interface DirectionCalendar {
    var breakfast: Calendar?
    var lunch: Calendar?
    var dinner: Calendar?
    var beforeBed: Calendar?
}