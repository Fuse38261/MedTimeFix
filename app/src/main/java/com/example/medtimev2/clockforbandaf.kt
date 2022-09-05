package com.example.medtimev2

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private var f: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockforbandafBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        val actionBar = supportActionBar
        actionBar!!.title = "ผู้จ่ายยา"
        actionBar.setDisplayHomeAsUpEnabled(true)

        binding.selectTimeBtn1.setOnClickListener {
            showTimePicker()
        }

        binding.selectTimeBtn2.setOnClickListener {
            showTimePicker2()
       }

        binding.selectTimeBtn3.setOnClickListener {
            showTimePicker3()
        }

        binding.finishBtn.setOnClickListener{
            setAlarm()
        }
    }

    private fun setAlarm() {

        var AlarmManagerArray = arrayOfNulls<AlarmManager>(24)
        val intentArray = ArrayList<PendingIntent>()

        while (f<4){
            val intent = Intent(this, AlarmReceiver::class.java)

            pendingIntent = PendingIntent.getBroadcast( this, f, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

            Log.d("FuseTesting", calendar.timeInMillis.toString())

            AlarmManagerArray[f] = getSystemService(ALARM_SERVICE) as AlarmManager

            AlarmManagerArray[f]!!.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            intentArray.add(pendingIntent)

            f++
        }

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
                binding.clockview1.text =String.format("%02d",picker.hour ) + " : " + String.format("%02d",picker.minute)+"AM"
            }

            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }


    }
    private fun showTimePicker2() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(supportFragmentManager,"Med")

        picker.addOnPositiveButtonClickListener{

            if(picker.hour > 12) {


                binding.clockview2.text = String.format("%02d",picker.hour - 12) + " : " + String.format("%02d",picker.minute)+"PM"


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
    private fun showTimePicker3() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(supportFragmentManager,"Med")

        picker.addOnPositiveButtonClickListener{

            if(picker.hour > 12) {


                binding.clockview3.text = String.format("%02d",picker.hour - 12) + " : " + String.format("%02d",picker.minute)+"PM"


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
