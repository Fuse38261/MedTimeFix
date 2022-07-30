package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.medtimev2.databinding.ActivityClockforbandafBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

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
    }
}
