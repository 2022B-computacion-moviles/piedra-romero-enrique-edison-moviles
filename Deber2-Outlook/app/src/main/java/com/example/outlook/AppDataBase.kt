package com.example.outlook



import android.content.Context
import androidx.room.Database
import androidx.room.Room
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
        /*private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                 .fallbackToDestructiveMigration()
                 .build()
                instance = db
                return db
            }
        }*/
    }
}