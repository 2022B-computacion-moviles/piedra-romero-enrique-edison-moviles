package com.example.outlook

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private lateinit var seccionViewModel: SeccionViewModel
    private lateinit var recyclerViewSecciones: RecyclerView
    private lateinit var adapter: RecyclerViewAdapterSeccion

    private lateinit var database: AppDataBase
    var secciones = emptyList<Seccion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database= Room.databaseBuilder(
            application, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        database.seccionDao.insert(Seccion(nameseccion = "Bandeja de entrada"))
        database.seccionDao.insert(Seccion(nameseccion = "Elementos enviados"))
        database.seccionDao.insert(Seccion(nameseccion = "Elementos eliminados"))
        database.seccionDao.insert(Seccion(nameseccion = "Correo no deseado"))
/*
        database.seccionDao.insertAll(Seccion(nameseccion = "Bandeja de entrada"))[0]
        database.seccionDao.insertAll(Seccion(nameseccion = "Correo no deseado"))[0]

 */





        recyclerViewSecciones = findViewById(R.id.rv_secciones)
        secciones=database.seccionDao.getAll()
        val adaptadorSeccion = RecyclerViewAdapterSeccion(
            secciones
        )
        recyclerViewSecciones.adapter = adaptadorSeccion

        val mLayoutManager = LinearLayoutManager(this)
        recyclerViewSecciones.layoutManager =mLayoutManager

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerViewSecciones.context,
            mLayoutManager.orientation
        )
        recyclerViewSecciones.addItemDecoration(mDividerItemDecoration)
        adaptadorSeccion.notifyDataSetChanged()


        /*

        database.seccionDao.getAll().observe(this, Observer {
            secciones = it

            val adaptadorSeccion = RecyclerViewAdapterSeccion(
                secciones
            )
            recyclerViewSecciones.adapter = adaptadorSeccion
            adaptadorSeccion.notifyDataSetChanged()
        })*/



        AlertDialog.Builder(this)
            .setTitle("Modificado")
            .setMessage("Con Éxito ${secciones.size}")
            .setPositiveButton("Aceptar") { dialog, which ->
                // acción cuando se presiona Aceptar
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // acción cuando se presiona Cancelar
            }
            .create()
            .show()



        /*
val secciones = database.seccionDao.getAll()
var text_s=""
secciones.forEach { seccion ->
    text_s+="${seccion.id}, ${seccion.nameseccion}\n"
}*/

        /*

        recyclerView = findViewById(R.id.rv_secciones)
        seccionViewModel = ViewModelProvider(this).get(SeccionViewModel::class.java)

        seccionViewModel.seccionsDefault() // Llamada al método antes de inicializar el adaptador

        val secciones = seccionViewModel.seccionesLiveData.value ?: emptyList()
        adapter = RecyclerViewAdapterSeccion(secciones)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)


        seccionViewModel.seccionsDefault()

        AlertDialog.Builder(this)
            .setTitle("Modificado")
            .setMessage("Con Éxito ${secciones.size}")
            .setPositiveButton("Aceptar") { dialog, which ->
                // acción cuando se presiona Aceptar
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // acción cuando se presiona Cancelar
            }
            .create()
            .show()


         */


    }
}