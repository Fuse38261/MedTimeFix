package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class payer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payer)

        val actionBar = supportActionBar

        actionBar!!.title = "ผู้สร้างฉลาก"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val goToClock = findViewById<Button>(R.id.Nexttoclock)
        goToClock.setOnClickListener {
            val intent = Intent(this, chooseclock::class.java)
            startActivity(intent)
        }
    }
}