package com.example.outlook



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities=[Seccion::class, Correo::class],
    version=5,
    exportSchema = false
)

abstract class AppDataBase: RoomDatabase(){
    abstract val seccionDao: SeccionDao
    abstract val correoDao: CorreoDao

    companion object {
        const val DATABASE_NAME="db-outlook"
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        DATABASE_NAME
                    )
                        .allowMainThreadQueries() // Solo para prueba, no recomendado para producción
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }



    }
}