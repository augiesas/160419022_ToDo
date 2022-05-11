package id.ac.ubaya.todo.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.todo.model.TodoDatabase

val DB_NAME = "tododb"

fun buildDB(context: Context):TodoDatabase{
    val db = Room.databaseBuilder(context,TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
    return db
}

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}

val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
        // Karena pada mysql tidak ada yang namanya boolean yang ada adalah TINYINT
        // yang mana sama seperti INT tetapi memiliki range yang lebih kecil yaitu hanya 0 dan 1
        // sehingga menggunakan INTEGER tidak masalah
    }
}