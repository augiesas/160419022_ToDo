package id.ac.ubaya.todo.view

import android.view.View
import android.webkit.WebSettings
import android.widget.CompoundButton
import id.ac.ubaya.todo.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo)
}

interface TodoEditClickListener{
    fun onEditClick(v: View)
}

interface RadioClick{
    fun onRadioClick(v:View, priority: Int, obj: Todo)
}

interface TodoSaveChangesClick{
    fun onTodoSaveChangesClick(v:View, obj:Todo)
}