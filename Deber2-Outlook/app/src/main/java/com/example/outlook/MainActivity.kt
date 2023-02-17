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

    //private lateinit var recyclerViewSecciones: RecyclerView
    private lateinit var adaptadorSeccion: RecyclerViewAdapterSeccion

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


        val recyclerViewSecciones = findViewById<RecyclerView>(R.id.rv_secciones)

        secciones=database.seccionDao.getAll()

        if(secciones.isEmpty()){
            database.seccionDao.insert(Seccion(nameseccion = "Bandeja de entrada"))
            database.seccionDao.insert(Seccion(nameseccion = "Elementos enviados"))
            database.seccionDao.insert(Seccion(nameseccion = "Elementos eliminados"))
            database.seccionDao.insert(Seccion(nameseccion = "Correo no deseado"))
            secciones=database.seccionDao.getAll()
        }
        adaptadorSeccion = RecyclerViewAdapterSeccion(secciones)
        recyclerViewSecciones.adapter = adaptadorSeccion

        val mLayoutManager = LinearLayoutManager(this)
        recyclerViewSecciones.layoutManager =mLayoutManager

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerViewSecciones.context,
            mLayoutManager.orientation
        )
        recyclerViewSecciones.addItemDecoration(mDividerItemDecoration)
        adaptadorSeccion.notifyDataSetChanged()


        registerForContextMenu(recyclerViewSecciones)

    }

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

        // Obtiene el objeto Seccion correspondiente al índice seleccionado
        seccionaux = adaptadorSeccion.getItem(id)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_seccion_eliminar->{
                eliminarSeccion(seccionaux)
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }


    fun eliminarSeccion(seccion: Seccion) {
        database.seccionDao.delete(seccion)
        adaptadorSeccion.notifyDataSetChanged()

    }

}