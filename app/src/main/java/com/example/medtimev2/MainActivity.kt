package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Date.parse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val firstActButton = findViewById<Button>(R.id.btnrecieve)
        firstActButton.setOnClickListener {
            val intent1 = Intent(this, reciever::class.java)
            startActivity(intent1)
        }

        val secondActButton = findViewById<Button>(R.id.btnpay)
        secondActButton.setOnClickListener {
            val intent1 = Intent(this, payer::class.java)
            startActivity(intent1)
        }

        printRecordDeserialized()
    }

    private fun printRecordDeserialized() {
        var record = Record()
        record.name = "Paracetamol"
        record.property = "used to treat fever and mild to moderate pain"
        record.warning = "have liver or kidney problems"
        record.countPerTime = 1.0
        record.timePerDay = 3
        record.direction = Direction.BEFORE_BED

        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.getDefault())
        val directionDate = DirectionDate()
        directionDate.beforeBed = Instant.parse("2022-02-01T14:23:05+07:00")

        record.directionDate = directionDate

        try {
            val json = Json.encodeToString(record)
            Log.d(TAG, json)
        } catch (err: Error) {
            Log.e(TAG, err.message!!)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
