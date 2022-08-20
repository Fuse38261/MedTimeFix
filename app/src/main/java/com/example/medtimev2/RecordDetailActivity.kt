package com.example.medtimev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RecordDetailActivity(var isChecked: Boolean) : AppCompatActivity() {
    var label: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_view)

        label = findViewById(R.id.nameoutput)
        var nameRecord = RecordService.default.records.get(0)
        label?.text = nameRecord.name

        label = findViewById(R.id.countoutput)
        var countRecord = RecordService.default.records.get(0)
        label?.id = countRecord.timePerDay



    }
}