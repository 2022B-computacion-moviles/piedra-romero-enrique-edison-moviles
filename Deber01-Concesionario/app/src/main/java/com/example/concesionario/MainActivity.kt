package com.example.concesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var adaptador: ArrayAdapter<BConcesionario>
    val arreglo:ArrayList<BConcesionario> =BBaseDatosMemoria.arregloBConcesionario
    var idItemSeleccionado=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listView=findViewById<ListView>(R.id.lv_concesionarios)
        //                          contexto      como se va a ver (XML)
        adaptador= ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglo)
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()

        val botonAniadirListView=findViewById<Button>(R.id.btn_crear_concesionario)
        botonAniadirListView
            .setOnClickListener {
                agregarConcesionario(adaptador)
            }

        registerForContextMenu(listView)


    }

    //idItemSeleccion para saber que elemento(del listview) escogió
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


    //opcion seleccionada del menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.item_btn_editar->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.item_btn_eliminar->{
                eliminarConcesionario(idItemSeleccionado)
                return true
            }
            R.id.item_btn_ver->{
                "${idItemSeleccionado}"
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }




    fun agregarConcesionario(adaptador: ArrayAdapter<BConcesionario>){
        BBaseDatosMemoria.arregloBConcesionario.add(BConcesionario("Conc5",
            BBaseDatosMemoria.formatoFecha.parse("02/07/2001"), 555))
        adaptador.notifyDataSetChanged() //Para que se cambie en la pantalla
    }

    fun eliminarConcesionario(position: Int) {
        arreglo.removeAt(position)
        adaptador.notifyDataSetChanged()
    }



}