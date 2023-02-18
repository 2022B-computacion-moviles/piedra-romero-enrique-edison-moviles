package com.example.outlook

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.outlook.adapter.SeccionAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDataBase
    var secciones = emptyList<Seccion>()

    var idItemSeleccionado=0
    lateinit var seccionaux: Seccion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database= Room.databaseBuilder(
            application, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        secciones = database.seccionDao.getAll().toMutableList()

        if(secciones.isEmpty()){
            database.seccionDao.insert(Seccion(nameseccion = "Bandeja de entrada"))
            database.seccionDao.insert(Seccion(nameseccion = "Elementos enviados"))
            database.seccionDao.insert(Seccion(nameseccion = "Elementos eliminados"))
            database.seccionDao.insert(Seccion(nameseccion = "Correo no deseado"))
            secciones = database.seccionDao.getAll().toMutableList()
        }

        val recyclerViewSecciones = findViewById<RecyclerView>(R.id.rv_secciones)
        initializeRecyclerView(secciones,recyclerViewSecciones)
    }


    private fun initializeRecyclerView(list: List<Seccion>, recyclerView: RecyclerView) {
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=SeccionAdapter(list)

    }


}