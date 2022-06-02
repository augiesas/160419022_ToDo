package id.ac.ubaya.todo.model

import androidx.room.*

@Dao
    interface TodoDao{
        // Place for queries
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(vararg todo:Todo)

        @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
        suspend fun selectAllTodo():List<Todo>

        @Query("SELECT * FROM todo WHERE uuid= :id")
        suspend fun selectTodo(id:Int):Todo

        @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid=:uuid")
        suspend fun update(title:String, notes:String, priority:Int, uuid:Int)

        @Query("UPDATE todo SET is_done=:isdone WHERE uuid=:uuid")
        suspend fun updateDone(isdone:Int, uuid: Int)

        @Delete
        suspend fun deleteTodo(todo: Todo)
    }
