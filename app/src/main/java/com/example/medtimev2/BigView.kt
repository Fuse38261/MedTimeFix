package com.example.medtimev2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BigView : AppCompatActivity() {

    lateinit var tts: TextToSpeech

    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_big_view)
        // Import information from record
        val intent = getIntent()
        val recordIndex = intent.getIntExtra(BigView.RECORD_INDEX_INTENT_NAME, -1)

        val record = RecordService.default.records[recordIndex]

        val name: TextView = findViewById(R.id.viewname)
        val time: TextView = findViewById(R.id.viewtime)
        val med: TextView = findViewById(R.id.viewmed)
        val warn: TextView = findViewById(R.id.viewwarning)
        val prop: TextView = findViewById(R.id.viewprop)
        val meal: TextView = findViewById(R.id.viewMeal)

        name.text = record.name
        prop.text = record.property
        warn.text  = record.warning
        med.text = record.timePerDay.toString()
        time.text = record.countPerTime.toString()
        meal.text = record.timeMeal

        // Btn for select time
        val selecttime = findViewById<Button>(R.id.btntime)
        selecttime.setOnClickListener {
            val Intent = Intent(this,clockforbandaf::class.java)
            startActivity(Intent)
        }

        // Text-To-Speech
        val bl1 = findViewById<ImageButton>(R.id.ttsBtn1)

        // Set Btn to react when clicked
        bl1.setOnClickListener{
            tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
                if(it==TextToSpeech.SUCCESS) {
                    tts.language = Locale.US
                    tts.setSpeechRate(1.0f)
                    tts.speak(name.text.toString(), TextToSpeech.QUEUE_ADD, null)
                }
            })
        }
    }

    companion object {
        const val RECORD_INDEX_INTENT_NAME = "RECORD_INDEX"
    }
}