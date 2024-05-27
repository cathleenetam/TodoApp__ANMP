package com.duodinamika.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.duodinamika.todoapp.model.Todo
import com.duodinamika.todoapp.model.TodoDatabase
import com.duodinamika.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModels(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<Todo>()
    private val job = Job()


//    fun fetch(uuid:Int) {
//        launch {
//            val db = buildDb(getApplication())
//            todoLD.value =  db.todoDao().selectTodo(uuid)
//        }
//    }

    fun fetch(uuid: Int) {
        // Launch a coroutine in the background
        CoroutineScope(Dispatchers.Default).launch {
            // Perform background work in the IO dispatcher
            val todo = withContext(Dispatchers.IO) {
                val db = buildDb(getApplication())
                db.todoDao().selectTodo(uuid)
            }

            // Switch to the main thread to update LiveData
            withContext(Dispatchers.Main) {
                todoLD.value = todo
            }
        }
    }



    fun addTodo(todo:Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }

    fun update(todo: Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().updateTodo(todo)
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


}