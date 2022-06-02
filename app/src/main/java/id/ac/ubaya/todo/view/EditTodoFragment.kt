package id.ac.ubaya.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.todo.R
import id.ac.ubaya.todo.databinding.FragmentEditTodoBinding
import id.ac.ubaya.todo.databinding.TodoItemLayoutBinding
import id.ac.ubaya.todo.model.Todo
import id.ac.ubaya.todo.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(), RadioClick, TodoSaveChangesClick {
    private lateinit var viewmodel:DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.radioListener = this
        dataBinding.listener = this

        viewmodel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewmodel.fetch(uuid)

        dataBinding.txtJudulTodo.text = "Edit Todo"
        dataBinding.btnAdd.text = "Save Change"

//        btnAdd.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            viewmodel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
//
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

        observerViewModel()
    }

    private fun observerViewModel() {
        viewmodel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            when (it.priority) {
//                3 -> radioHigh.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioLow.isChecked = true
//            }
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewmodel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}