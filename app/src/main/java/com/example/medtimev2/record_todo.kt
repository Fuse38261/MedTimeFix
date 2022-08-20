package com.example.medtimev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medtimev2.databinding.ActivityRecordTodoBinding


class record_todo : AppCompatActivity() {

    private lateinit var binding: ActivityRecordTodoBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_todo)

        binding.rvTodoItems.adapter = todoAdapter

    }
}