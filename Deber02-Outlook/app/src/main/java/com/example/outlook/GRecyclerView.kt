package com.example.outlook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        val listCorrreos = arrayListOf<Correo>()
        listCorrreos
            .add(Correo("Enrique","Eguez", "Hola Pof"))
        listCorrreos
            .add(Correo("Enrique","Eguez 2", "Hola Pof2"))

        val recyclerView = findViewById<RecyclerView>(R.id.grv_correos)
        inicializarRecyclerView(listCorrreos,recyclerView)
    }
    fun inicializarRecyclerView(
        lista:ArrayList<Correo>,
        recyclerView: RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorElements(
            this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
    
}