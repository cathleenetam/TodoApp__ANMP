package com.duodinamika.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title") //dipakai kalo nama di class dan db beda
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done")
    var is_done:Int
    // xxx
    )
{
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}
