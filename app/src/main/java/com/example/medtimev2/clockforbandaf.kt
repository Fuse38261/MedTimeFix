package com.example.medtimev2

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.medtimev2.databinding.ActivityClockforbandafBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class clockforbandaf : AppCompatActivity() {

    private lateinit var binding: ActivityClockforbandafBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockforbandafBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        val actionBar = supportActionBar
        actionBar!!.title = "ผู้จ่ายยา"
        actionBar.setDisplayHomeAsUpEnabled(true)

//        binding.selectTimeBtn1.setOnClickListener {
//            showTimePicker()
//        }
//
//        binding.selectTimeBtn2.setOnClickListener {
//            showTimePicker()
//        }

        binding.selectTimeBtn3.setOnClickListener {
            showTimePicker()
        }

        binding.finishBtn.setOnClickListener{
            setAlarm()
        }
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        Log.d("FuseTesting", calendar.timeInMillis.toString())

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )

        Toast.makeText(this,"ได้ตั้งนาฬิกาปลุกแล้ว",Toast.LENGTH_SHORT).show()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val name : CharSequence = "medtimeReminderChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("medtime",name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
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

            if(picker.hour > 12) {

                binding.clockview1.text = String.format("%02d",picker.hour - 12) + " : " + String.format("%02d",picker.minute)+"PM"


            } else {
                String.format("%02d",picker.hour - 12) + " : " + String.format("%02d",picker.minute)+"AM"
            }

            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }

    }
}
