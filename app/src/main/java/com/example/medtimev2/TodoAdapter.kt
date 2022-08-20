package com.example.medtimev2

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medtimev2.databinding.ItemViewBinding

class TodoAdapter (
    private val todos: MutableList<RecordDetailActivity>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private lateinit var binding: ItemViewBinding


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val nameoutput1 = binding.nameoutput
    private val checked1 = binding.checked


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: RecordDetailActivity){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(nameoutput1 : TextView, checked1 : Boolean){

        if (checked1){

            nameoutput1.paintFlags = nameoutput1.paintFlags or STRIKE_THRU_TEXT_FLAG

        }else{

            nameoutput1.paintFlags = nameoutput1.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()

        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {

            nameoutput1.text = curTodo.title
            checked1.isChecked = curTodo.isChecked
            toggleStrikeThrough(nameoutput1, curTodo.isChecked )
            checked1.setOnCheckedChangeListener { _, isChecked ->

                toggleStrikeThrough(nameoutput1, isChecked)
                curTodo.isChecked = !curTodo.isChecked


            }

        }
    }

    override fun getItemCount(): Int {

        return todos.size

    }
}