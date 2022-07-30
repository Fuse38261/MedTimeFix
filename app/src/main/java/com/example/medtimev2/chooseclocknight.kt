package com.example.medtimev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtimev2.databinding.ActivityChooseclocknightBinding
import com.example.medtimev2.databinding.ActivityClockforbandafBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class chooseclocknight : AppCompatActivity() {

    private lateinit var binding: ActivityChooseclocknightBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseclocknightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar

        actionBar!!.title = "ผู้จ่ายยา"

        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.selectTimeNight.setOnClickListener {

            showTimePicker()

        }
    }

    private fun showTimePicker() {

        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(supportFragmentManager,"Med")

        picker.addOnPositiveButtonClickListener{

            if(picker.hour > 12){

                binding.clockviewnight.text = String.format("%02d",picker.hour - 12) + " : " + String.format("%02d",picker.minute)+"PM"


            }else{
                String.format("%02d",picker.hour - 12) + " : " + String.format("%02d",picker.minute)+"AM"


            }

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }

    }
}
