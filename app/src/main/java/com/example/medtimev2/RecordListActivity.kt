package com.example.medtimev2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecordListActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)


        val recyclerView = findViewById<RecyclerView>(R.id.rvTodoItems)
        val records = RecordService.default.records

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TodoAdapter(records) {
            val intent = Intent(this, BigView::class.java)
            intent.putExtra(BigView.RECORD_INDEX_INTENT_NAME, it)
            startActivity(intent)
        }
    }
}