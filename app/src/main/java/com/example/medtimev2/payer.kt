package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.medtimev2.databinding.ItemViewBinding

class payer : AppCompatActivity() {

    private lateinit var binding: payer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payer)

        val actionBar = supportActionBar

        actionBar!!.title = "ผู้สร้างฉลาก"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val qrcode = findViewById<Button>(R.id.Nexttoqr)
        qrcode.setOnClickListener {
            val intent = Intent(this,RecordListActivity::class.java)
            startActivity(intent)
        }
    }
}