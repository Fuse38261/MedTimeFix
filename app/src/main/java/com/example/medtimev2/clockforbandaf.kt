package com.example.medtimev2

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medtimev2.databinding.ActivityClockforbandafBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class clockforbandaf : AppCompatActivity() {

    private lateinit var binding: ActivityClockforbandafBinding
    private lateinit var picker1: MaterialTimePicker
    private lateinit var picker2: MaterialTimePicker
    private lateinit var picker3: MaterialTimePicker
    private lateinit var picker4: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private var i : Int = 0

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
            i = 0
        }

        binding.selectTimeBtn2.setOnClickListener {
            showTimePicker2()
            i = 1
       }

        binding.selectTimeBtn3.setOnClickListener {
            showTimePicker3()
            i = 2
        }

        binding.selectTimeBtn4.setOnClickListener {
            showTimePicker4()
            i = 3
        }
        binding.finishBtn.setOnClickListener{
            setAlarm()
        }
    }

    private fun setAlarm() {
        // Create an array of PendingIntent objects
        val intentArray = mutableListOf<PendingIntent>()
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var f = 0

        while (f <= i) {

            val intent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, f, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            intentArray.add(pendingIntent)

            // Set the calendar instance to the selected time
            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
            calendar[Calendar.HOUR_OF_DAY] = getHour(f)
            calendar[Calendar.MINUTE] = getMinute(f)
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

            // Save the alarm time to shared preferences
            with(sharedPreferences.edit()) {
                putLong("alarmTime$f", calendar.timeInMillis)
                apply()
            }

            // Set the alarm using the PendingIntent and the AlarmManager
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            f++

        }

        Toast.makeText(this, "ได้ตั้งนาฬิกาปลุกแล้ว", Toast.LENGTH_SHORT).show()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val name : CharSequence = "medTimeReminderChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("medTime",name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showTimePicker() {

       /* val daySpinner = findViewById<Spinner>(R.id.day_spinner)
        val selectedDay = daySpinner.selectedItem.toString()
        val alarmTimeInMillis = calendar.timeInMillis*/

        picker1 = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(0)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker1.show(supportFragmentManager,"Med")

        picker1.addOnPositiveButtonClickListener{

            binding.clockview1.text = String.format("%02d",picker1.hour) + " : " + String.format("%02d",picker1.minute)


            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
            //calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeek(selectedDay))
            calendar[Calendar.HOUR_OF_DAY] = picker1.hour
            calendar[Calendar.MINUTE] = picker1.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }


    }
    private fun showTimePicker2() {


        picker2 = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(0)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker2.show(supportFragmentManager,"Med")

        picker2.addOnPositiveButtonClickListener{

            binding.clockview2.text = String.format("%02d",picker2.hour) + " : " + String.format("%02d",picker2.minute)

            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
            calendar[Calendar.HOUR_OF_DAY] = picker2.hour
            calendar[Calendar.MINUTE] = picker2.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }


    }

    private fun showTimePicker3() {
        picker3 = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(0)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker3.show(supportFragmentManager,"Med")

        picker3.addOnPositiveButtonClickListener{

            binding.clockview3.text = String.format("%02d",picker3.hour) + " : " + String.format("%02d",picker3.minute)

            calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"))
            calendar[Calendar.HOUR_OF_DAY] = picker3.hour
            calendar[Calendar.MINUTE] = picker3.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }
    }
    private fun showTimePicker4() {

        picker4 = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()

        picker4.show(supportFragmentManager,"Med")

        picker4.addOnPositiveButtonClickListener{

            binding.clockviewnight.text = String.format("%02d",picker4.hour) + " : " + String.format("%02d",picker4.minute)

            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker4.hour
            calendar[Calendar.MINUTE] = picker4.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }

    }
    private fun getDayOfWeek(day: String): Int {
        return when (day){
            "วันอาทิตย์" -> Calendar.SUNDAY
            "วันจันทร์" -> Calendar.MONDAY
            "วันอังคาร" -> Calendar.TUESDAY
            "วันพุธ" -> Calendar.WEDNESDAY
            "วันพฤหัสบดี" -> Calendar.THURSDAY
            "วันศุกร์" -> Calendar.FRIDAY
            "วันเสาร์" -> Calendar.SATURDAY
            else -> throw IllegalArgumentException("โปรดใส่ข้อมูลเกี่ยวกันวันในสัปดาห์: $day")
        }
    }

    // A helper function to get the hour of the selected time
    private fun getHour(index: Int): Int {
        return when (index) {
            0 -> picker1.hour
            1 -> picker2.hour
            2 -> picker3.hour
            3 -> picker4.hour
            else -> 0
        }
    }

    // A helper function to get the minute of the selected time
    private fun getMinute(index: Int): Int {
        return when (index) {
            0 -> picker1.minute
            1 -> picker2.minute
            2 -> picker3.minute
            3 -> picker4.minute
            else -> 0
        }
    }
}
