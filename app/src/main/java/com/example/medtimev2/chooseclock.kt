package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class chooseclock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooseclock)

        val actionBar = supportActionBar

        actionBar!!.title = "ผู้จ่ายยา"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val goToClock1 = findViewById<Button>(R.id.btnbefore)
        goToClock1.setOnClickListener {
            val intent = Intent(this, clockforbandaf ::class.java)
            startActivity(intent)
        }
        val goToClock2 = findViewById<Button>(R.id.btnafter)
        goToClock2.setOnClickListener {
            val intent = Intent(this, clockforbandaf ::class.java)
            startActivity(intent)
        }
        val goToClocknight = findViewById<Button>(R.id.btnnight)
        goToClocknight.setOnClickListener {
            val intent = Intent(this, chooseclocknight ::class.java)
            startActivity(intent)
        }
    }
}