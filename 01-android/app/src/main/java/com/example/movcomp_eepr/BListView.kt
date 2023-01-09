package com.example.movcomp_eepr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo:ArrayList<BEntrenador> =BBaseDatosMemoria.arregloBEntrenador
    //
    var idItemSeleccionado=0

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
        //Cuando se registre
        registerForContextMenu(listView)

    }

    //idItemSeleccion para saber que opcion escogio
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //LLenamos los opciones del menu
        val inflater=menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArrayListSeleccionado
        val info =menuInfo as AdapterView.AdapterContextMenuInfo
        val id= info.position
        idItemSeleccionado=id

    }

    fun aniadirEntrenador(adaptador: ArrayAdapter<BEntrenador>){
        arreglo.add(BEntrenador("Ejemplo","a@a.com"))
        adaptador.notifyDataSetChanged() //Para que se cambie en la pantalla
    }
}


