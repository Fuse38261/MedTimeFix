package com.example.medtimev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RecordDetailActivity : AppCompatActivity() {
    var label: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_detail2)

        label = findViewById(R.id.textView2)
        var firstRecord = RecordService.default.records.get(0)
        label?.text = firstRecord.name
    }
}