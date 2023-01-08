package com.example.movcomp_eepr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo:ArrayList<BEntrenador> =BBaseDatosMemoria.arregloBEntrenador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView=findViewById<ListView>(R.id.lv_list_view)
        //                          contexto      como se va a ver (XML)
        val adaptador=ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglo)
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()

        val botonAniadirListView=findViewById<Button>(R.id.btn_anadir_list_view)
        botonAniadirListView
            .setOnClickListener {
                aniadirEntrenador(adaptador)
            }
    }

    fun aniadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(BEntrenador("Ejemplo","a@a.com"))
        adaptador.notifyDataSetChanged() //Para que se cambie en la pantalla
    }
}


