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

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDataBase
    var secciones = ArrayList<Seccion>()

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

        val recyclerViewSecciones = findViewById<RecyclerView>(R.id.rv_secciones)
        secciones = database.seccionDao.getAll().toMutableList() as ArrayList<Seccion>

        if(secciones.isEmpty()){
            database.seccionDao.insert(Seccion(nameseccion = "Bandeja de entrada"))
            database.seccionDao.insert(Seccion(nameseccion = "Elementos enviados"))
            database.seccionDao.insert(Seccion(nameseccion = "Elementos eliminados"))
            database.seccionDao.insert(Seccion(nameseccion = "Correo no deseado"))
            secciones = database.seccionDao.getAll().toMutableList() as ArrayList<Seccion>
        }
        initializeRecyclerView(secciones,recyclerViewSecciones)
    }


    private fun initializeRecyclerView(list: ArrayList<Seccion>, recyclerView: RecyclerView) {
        val adapter = RecyclerViewAdapterSeccion(list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }


/*
    //idItemSeleccion para saber que elemento(del listview) escogió
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_seccion, menu)
        val info =menuInfo as AdapterView.AdapterContextMenuInfo
        val id= info.position
        idItemSeleccionado=id


    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_seccion_eliminar->{

                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }



*/
}