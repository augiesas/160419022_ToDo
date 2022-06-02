package id.ac.ubaya.todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.todo.R
import id.ac.ubaya.todo.databinding.TodoItemLayoutBinding
import id.ac.ubaya.todo.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*
import java.util.zip.Inflater

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Int) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(), TodoCheckedChangeListener, TodoEditClickListener {
    class TodoListViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater,parent,false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this
//        holder.view.imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }

//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if (isChecked){
//                adapterOnClick(todoList[position].uuid)
//            }
//        }
    }

    override fun getItemCount() = todoList.size

    fun updateTodoList(newTodoList:List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            adapterOnClick(obj.uuid)
        }
    }

    override fun onEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}