package com.example.medtimev2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class payer : AppCompatActivity() {

    private lateinit var binding: payer
    private lateinit var nameText: EditText
    private lateinit var propertiesText: EditText
    private lateinit var warningText: EditText
    private lateinit var countPerTimeText: EditText
    private lateinit var timePerDayText: EditText
    private lateinit var beforeMealCheckBox: CheckBox
    private lateinit var afterMealCheckBox: CheckBox
    private lateinit var beforeSleepCheckBox: CheckBox
    private lateinit var medImage: ImageView

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payer)

        val actionBar = supportActionBar
        actionBar!!.title = "ผู้สร้างฉลาก"
        actionBar.setDisplayHomeAsUpEnabled(true)


        // Record the information
        nameText = findViewById(R.id.inputname)
        propertiesText = findViewById(R.id.inputprop)
        warningText = findViewById(R.id.inputwarn)
        countPerTimeText = findViewById(R.id.inputmed)
        timePerDayText = findViewById(R.id.inputtime)
        beforeMealCheckBox = findViewById(R.id.checkBox1)
        afterMealCheckBox = findViewById(R.id.checkBox2)
        beforeSleepCheckBox = findViewById(R.id.checkBox3)

        medImage = findViewById(R.id.medImageView)

        // Set up click listener for button to select image
        findViewById<Button>(R.id.selectImageBtn).setOnClickListener {
            Log.d("MyActivity", "Select image button has been clicked.")
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )
        }

        val qrcode = findViewById<Button>(R.id.Nexttoqr)
        qrcode.setOnClickListener {
            val record = Record()
            record.name = nameText.text.toString()
            record.property = propertiesText.text.toString()
            record.warning = warningText.text.toString()
            record.countPerTime = countPerTimeText.text.toString().toDouble()
            record.timePerDay = timePerDayText.text.toString().toInt()
            if (beforeMealCheckBox.isChecked) {

                record.timeMeal = "ก่อนอาหาร"

            } else if (afterMealCheckBox.isChecked) {

                record.timeMeal = "หลังอาหาร"


            } else if (beforeMealCheckBox.isChecked && beforeSleepCheckBox.isChecked) {

                record.timeMeal = "ก่อนอาหารและก่อนนอน"

            } else if (afterMealCheckBox.isChecked && beforeSleepCheckBox.isChecked) {

                record.timeMeal = "หลังอาหารและก่อนนอน"

            } else {

                record.timeMeal = "ก่อนนอน"



            }
            val intent = Intent(this, Qrcode::class.java)
            intent.putExtra(Qrcode.RECORD_INTENT_NAME, record)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val uri = data.data

            Glide.with(this).load(uri).into(medImage)
        }
    }
}