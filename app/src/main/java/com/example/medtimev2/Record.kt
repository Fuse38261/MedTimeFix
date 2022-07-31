package com.example.medtimev2

import java.util.*
import kotlinx.serialization.Serializable
import kotlinx.datetime.*

@Serializable
class Record {
    var name: String = ""
    var property: String = ""
    var warning: String = ""
    var countPerTime: Double = 0.0
    var timePerDay: Int = 0
    var direction: Direction = Direction.UNDEFINED
    var directionDate: DirectionDate? = null

    fun getAlerts(): List<Instant> {
        if (this.directionDate == null) {
            return listOf()
        }

        return when (this.direction) {
            Direction.BEFORE_MEAL, Direction.AFTER_MEAL -> listOf(this.directionDate!!.breakfast, this.directionDate!!.lunch, this.directionDate!!.dinner)
            Direction.BEFORE_BED -> listOf(this.directionDate!!.beforeBed)
            Direction.UNDEFINED -> listOf()
        }.filterNotNull()
    }
}

@Serializable
enum class Direction {
    BEFORE_MEAL, AFTER_MEAL, BEFORE_BED, UNDEFINED
}

@Serializable
data class DirectionDate(
    var breakfast: Instant? = null,
    var lunch: Instant? = null,
    var dinner: Instant? = null,
    var beforeBed: Instant? = null
)