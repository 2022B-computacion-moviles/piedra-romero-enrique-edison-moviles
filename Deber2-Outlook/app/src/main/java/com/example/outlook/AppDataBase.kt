package com.example.outlook



import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities=[Seccion::class],
    version=5,
    exportSchema = false
)

abstract class AppDataBase: RoomDatabase(){
    abstract val seccionDao: SeccionDao
    companion object {
        const val DATABASE_NAME="db-outlook"
    }
}