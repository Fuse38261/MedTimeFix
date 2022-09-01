package com.example.medtimev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BigView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_view)

        val intent = getIntent()
        val recordIndex = intent.getIntExtra(BigView.RECORD_INDEX_INTENT_NAME, -1)

        val record = RecordService.default.records[recordIndex]

        val name: TextView = findViewById(R.id.viewname)
        val time: TextView = findViewById(R.id.viewtime)
        val med: TextView = findViewById(R.id.viewmed)
        val warn: TextView = findViewById(R.id.viewwarning)
        val prop: TextView = findViewById(R.id.viewprop)

        name.text = record.name
        prop.text = record.property
        warn.text  = record.warning
        med.text = record.timePerDay.toString()
        time.text = record.countPerTime.toString()
    }

    companion object {
        const val RECORD_INDEX_INTENT_NAME = "RECORD_INDEX"
    }
}