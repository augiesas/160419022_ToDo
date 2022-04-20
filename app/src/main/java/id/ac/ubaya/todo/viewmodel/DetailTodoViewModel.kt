package id.ac.ubaya.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import id.ac.ubaya.todo.model.Todo
import id.ac.ubaya.todo.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private var job = Job()

    fun addTodo(list:Todo){
        launch{
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "tododb").build()
            db.todoDao().insertAll(list)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}