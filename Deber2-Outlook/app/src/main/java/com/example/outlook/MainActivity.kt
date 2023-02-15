package com.example.outlook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        database= Room.databaseBuilder(
            application, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()*/
        database= Room.databaseBuilder(
            application, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        database.seccionDao.insert(Seccion(nameseccion="ENTRADA1"))

        val botonCrearBDD = findViewById<TextView>(R.id.text_elements)

        val seccion = database.seccionDao.getAll()
        var text_s=""
        seccion.forEach { seccion ->
            text_s+="${seccion.id}, ${seccion.nameseccion}\n"
        }
        botonCrearBDD.text=text_s






    }
}