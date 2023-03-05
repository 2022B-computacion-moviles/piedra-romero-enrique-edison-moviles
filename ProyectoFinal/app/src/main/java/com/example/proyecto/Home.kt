package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.adapter.RcVwAdapterInstruments
import com.example.proyecto.data.entity.Instrument
import com.example.proyecto.data.firebase.FirebaseGlobal
import com.example.proyecto.data.firebase.FirebaseInstruments
import com.example.proyecto.data.util.DataInstruments
import com.example.proyecto.sesion.Sesion
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    private lateinit var selectedInstrument: Instrument

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Ingresa datos
        for (instrument in DataInstruments.instrumentsData) {
            FirebaseGlobal.firebaseInstruments.create(instrument)
        }

        //RecyclerView
        val rvInstruments=findViewById<RecyclerView>(R.id.rv_intruments)
        FirebaseGlobal.firebaseInstruments.getAllInstruments { instruments ->
            initializeRecyclerView(instruments, rvInstruments)
            registerForContextMenu(rvInstruments)
        }


        //Header & Auth
        val btnHeader = findViewById<Button>(R.id.header_button)
        val logoHeader = findViewById<ImageView>(R.id.header_logo)
        Sesion.sesionCurrent(this,btnHeader)
        Sesion.menuSesion(this,btnHeader)
        Sesion.headerLogo(this, logoHeader)


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(
        list: ArrayList<Instrument>,
        recyclerView: RecyclerView
    ) {
        //val manager= LinearLayoutManager(this)
        val manager= GridLayoutManager(this,2)
        val decoration= DividerItemDecoration(this, manager.orientation)

        val adapter = RcVwAdapterInstruments(this, list, {intrument ->onIntrumentSelected(intrument)})

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(decoration)

        adapter.notifyDataSetChanged()
    }




    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_instrument_ver -> {
                startActivity(Intent(this, ViewInstrument::class.java).apply {
                    putExtra("instrumentSelected",selectedInstrument)
                })


                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    //Formas de obtener un elemento

    //1ERA: al mantener pulsado y luego soltar
    fun setSelectedInstrumentCode(instrumentSelectedv1: Instrument) {
        selectedInstrument = instrumentSelectedv1
        //Toast.makeText(this, "Usuario seleccionado: ${selectedInstrumentCode}", Toast.LENGTH_SHORT).show()
    }

    //2DA: al hacer clic
    fun onIntrumentSelected(instrumentSelected: Instrument) {
        //Toast.makeText(this, "${instrumentSelected.codeInstrument}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, ViewInstrument::class.java).apply {
            putExtra("instrumentSelected",instrumentSelected)
        })
    }




}