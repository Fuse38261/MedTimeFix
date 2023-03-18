package com.example.medtimev2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payer)

        val actionBar = supportActionBar
        actionBar!!.title = "ผู้กรอกข้อมูลยา"
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


        val qrcode = findViewById<Button>(R.id.Nexttoqr)
        qrcode.setOnClickListener {
            if (nameText.text.toString().isEmpty() ||
                propertiesText.text.toString().isEmpty() ||
                warningText.text.toString().isEmpty() ||
                countPerTimeText.text.toString().isEmpty() ||
                timePerDayText.text.toString().isEmpty()
            ) {

                Toast.makeText(this, "โปรดใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()

            } else {

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
    }
}