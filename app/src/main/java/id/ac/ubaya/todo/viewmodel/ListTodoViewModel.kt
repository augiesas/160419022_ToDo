package id.ac.ubaya.todo.viewmodel

import android.app.Application
import android.provider.DocumentsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.todo.model.Todo
import id.ac.ubaya.todo.model.TodoDatabase
import id.ac.ubaya.todo.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext:CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(){
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch{
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun update(isdone:Int, uuid:Int){
        launch {
            val db = buildDB(getApplication())
            db.todoDao().updateDone(isdone, uuid)
        }
    }

    fun clearTask(todo: Todo){
        launch {
            val db = buildDB(getApplication())

            db.todoDao().deleteTodo(todo)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
}