package com.duodinamika.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.duodinamika.todoapp.R
import com.duodinamika.todoapp.databinding.FragmentEditTodoBinding
import com.duodinamika.todoapp.model.Todo
import com.duodinamika.todoapp.viewmodel.DetailTodoViewModels
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class EditTodoFragment : Fragment(), TodoSaveChangesClick, RadioClick {

    private lateinit var viewModel: DetailTodoViewModels
    private lateinit var binding:FragmentEditTodoBinding
    private lateinit var txtTitle: EditText
    private lateinit var txtNotes: EditText
    private lateinit var txtJudulTodo: TextView
    private lateinit var btnAdd: Button
    private lateinit var radioLow:RadioButton
    private lateinit var radioMedium:RadioButton
    private lateinit var radioHigh:RadioButton
    private lateinit var radioGroupPriority:RadioGroup
    private var todo: Todo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTodoBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioListener = this
        binding.saveListener = this

        viewModel = ViewModelProvider(this).get(DetailTodoViewModels::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()


//        txtTitle = view.findViewById(R.id.txtTitle)
//        txtNotes = view.findViewById(R.id.txtNote)
//        txtJudulTodo = view.findViewById(R.id.txtJudulTodo)
//        btnAdd = view.findViewById(R.id.btnAdd)
//        radioGroupPriority = view.findViewById(R.id.radioGroupPriority)
//        radioHigh = view.findViewById(R.id.radioHigh)
//        radioMedium = view.findViewById(R.id.radioMedium)
//        radioLow = view.findViewById(R.id.radioLow)

//        txtJudulTodo.text = "Edit Todo"
//        btnAdd.text = "Save Changes"

//        btnAdd.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//
//            todo?.notes = txtNotes.text.toString()
//            todo?.title = txtTitle.text.toString()
//            todo?.priority = radio.tag.toString().toInt()
//            todo?.uuid = uuid
//            viewModel.update(todo!!)
//
////            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
////                radio.tag.toString().toInt(), uuid)
//
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//
//        }



    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner) {
            binding.todo=it

//            todo=it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//            when (it.priority) {
//                1 -> radioLow.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioHigh.isChecked = true
//            }
        }
    }

    override fun onRadioClick(v: View) {
        binding.todo!!.priority = v.tag.toString().toInt()
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
//        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        viewModel.update(binding.todo!!)
        //Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Snackbar.make(requireContext(), v, "Todo Updated", Snackbar.LENGTH_SHORT).show()
    }

}