package com.example.medtimev2
import android.os.Parcel
import android.os.Parcelable
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
class Record() : Parcelable {
    var name: String = ""
    var property: String = ""
    var warning: String = ""
    var countPerTime: Double = 0.0
    var timePerDay: Int = 0
    var timeMeal : String = ""
    var direction: Direction = Direction.UNDEFINED
    var directionDate: DirectionDate? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        property = parcel.readString().toString()
        warning = parcel.readString().toString()
        countPerTime = parcel.readDouble()
        timePerDay = parcel.readInt()
        timeMeal = parcel.readString().toString()

        //direction = Direction.valueOf(parcel.readString().toString())
        //directionDate = parcel.readParcelable(DirectionDate.javaClass.classLoader)
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(property)
        parcel.writeString(warning)
        parcel.writeDouble(countPerTime)
        parcel.writeInt(timePerDay)
        parcel.writeString(timeMeal)
        parcel.writeString(direction.name)
        parcel.writeParcelable(directionDate, 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Record> {
        override fun createFromParcel(parcel: Parcel): Record {
            return Record(parcel)
        }

        override fun newArray(size: Int): Array<Record?> {
            return arrayOfNulls(size)
        }
    }
}

@Serializable
enum class Direction {
    BEFORE_MEAL, AFTER_MEAL, BEFORE_BED, UNDEFINED
}

@Serializable
class DirectionDate : Parcelable {
    var breakfast: Instant? = null
    var lunch: Instant? = null
    var dinner: Instant? = null
    var beforeBed: Instant? = null

    constructor() {}

    constructor(parcel: Parcel) : this() {
        this.breakfast = (parcel.readLong() as Long?)?.let { Instant.fromEpochSeconds(it) }
        this.lunch = (parcel.readLong() as Long?)?.let { Instant.fromEpochSeconds(it) }
        this.dinner = (parcel.readLong() as Long?)?.let { Instant.fromEpochSeconds(it) }
        this.beforeBed = (parcel.readLong() as Long?)?.let { Instant.fromEpochSeconds(it) }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        breakfast?.let { parcel.writeLong(it.epochSeconds) }
        lunch?.let { parcel.writeLong(it.epochSeconds) }
        dinner?.let { parcel.writeLong(it.epochSeconds) }
        beforeBed?.let { parcel.writeLong(it.epochSeconds) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DirectionDate> {
        override fun createFromParcel(parcel: Parcel): DirectionDate {
            return DirectionDate(parcel)
        }

        override fun newArray(size: Int): Array<DirectionDate?> {
            return arrayOfNulls(size)
        }
    }
}