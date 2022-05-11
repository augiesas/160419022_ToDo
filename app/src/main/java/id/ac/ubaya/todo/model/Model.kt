package id.ac.ubaya.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "notes")
    var notes:String,
    @ColumnInfo(name = "priority")
    var priority: Int,
    @ColumnInfo(name = "is_done")
    var isdone: Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}