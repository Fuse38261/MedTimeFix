package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class reciever : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciever)

        val actionBar = supportActionBar

        actionBar!!.title = "ผู้รับยา"

        actionBar.setDisplayHomeAsUpEnabled(true)

        }
    }
