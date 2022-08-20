package com.example.medtimev2

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medtimev2.databinding.ItemViewBinding

class TodoAdapter (
    private val records: MutableList<Record>
) : RecyclerView.Adapter<TodoAdapter.RecordViewHolder>() {

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView
        val countView: TextView

        init {
            nameView = itemView.findViewById(R.id.nameoutput)
            countView = itemView.findViewById(R.id.countoutput)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = records[position]
        holder.nameView.text = record.name
        holder.countView.text = record.countPerTime.toString()
    }

    override fun getItemCount(): Int {
        return records.size
    }
}