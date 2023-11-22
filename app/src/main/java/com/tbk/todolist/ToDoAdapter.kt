package com.tbk.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.item_todo.view.*



class ToDoAdapter(
    private val todos: MutableList<ToDo>
): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(toDo: ToDo){
        todos.add(toDo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteToDo(){
        todos.removeAll{toDo->
            toDo.isChecked
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked:Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply{
            val tvToDoTitle : TextView = findViewById(R.id.tvToDoTitle)
            val cbDone: CheckBox = this.findViewById(R.id.cbDone)
            tvToDoTitle.text = curToDo.title
            cbDone.isChecked = curToDo.isChecked
            toggleStrikeThrough(tvToDoTitle,curToDo.isChecked)
            cbDone.setOnCheckedChangeListener{ _ ,isChecked ->
                toggleStrikeThrough(tvToDoTitle,isChecked)
                curToDo.isChecked = !curToDo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}