package com.example.medtimev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecordListActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)


        val recyclerView = findViewById<RecyclerView>(R.id.rvTodoItems)
        val records = RecordService.default.records
        /*val listname: String = findViewById<EditText>(R.id.inputname).toString()
        val listprop: String = findViewById<EditText>(R.id.inputprop).toString()
        val listwarn: String = findViewById<EditText>(R.id.inputwarn).toString()
        val listmed: String = findViewById<EditText>(R.id.inputmed).toString()
        val listtime: String = findViewById<EditText>(R.id.inputtime).toString()*/

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TodoAdapter(records, this)

        /*val intent = Intent(this@RecordListActivity,BigView::class.java)
        intent.putExtra("heading",listname)
        intent.putExtra("property",listprop)
        intent.putExtra("warning",listwarn)
        intent.putExtra("amount of time to eat",listtime)
        intent.putExtra("amount of medicine to eat",listmed)
        startActivity(intent)*/


    }
}