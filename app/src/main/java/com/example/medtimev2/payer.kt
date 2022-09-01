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
    private lateinit var nameText: EditText
    private lateinit var propertiesText: EditText
    private lateinit var warningText: EditText
    private lateinit var countPerTimeText: EditText
    private lateinit var timePerDayText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payer)

        val actionBar = supportActionBar
        actionBar!!.title = "ผู้สร้างฉลาก"
        actionBar.setDisplayHomeAsUpEnabled(true)

        nameText = findViewById(R.id.inputname)
        propertiesText = findViewById(R.id.inputprop)
        warningText = findViewById(R.id.inputwarn)
        countPerTimeText = findViewById(R.id.inputmed)
        timePerDayText = findViewById(R.id.inputtime)

        val qrcode = findViewById<Button>(R.id.Nexttoqr)
        qrcode.setOnClickListener {
            val record = Record()
            record.name = nameText.text.toString()
            record.property = propertiesText.text.toString()
            record.warning = warningText.text.toString()
            record.countPerTime = countPerTimeText.text.toString().toDouble()
            record.timePerDay = timePerDayText.text.toString().toInt()

            val intent = Intent(this,Qrcode::class.java)
            intent.putExtra(Qrcode.RECORD_INTENT_NAME, record)
            startActivity(intent)
        }
    }
}