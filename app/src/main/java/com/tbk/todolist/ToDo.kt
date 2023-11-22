package com.tbk.todolist

data class ToDo (
    val title: String,
    var isChecked: Boolean = false
)