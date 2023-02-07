package com.example.concesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

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
                //agregarConcesionario(adaptador)
                val nombre = findViewById<EditText>(R.id.input_conc_nombre)
                val fecha_inaguracion = findViewById<EditText>(R.id.input_conc_fecha)
                val porcentaje_personas_satisfechas = findViewById<EditText>(R.id.input_conc_porcentaje)
                val cantidad_empleados = findViewById<EditText>(R.id.input_conc_empleados)


                if (TextUtils.isEmpty(nombre.text) || TextUtils.isEmpty(fecha_inaguracion.text) || TextUtils.isEmpty(porcentaje_personas_satisfechas.text) || TextUtils.isEmpty(cantidad_empleados.text)) {
                    //No pasa nada
                }
                else{
                    //Parametros completos
                    agregarConcesionario(BConcesionario(
                        nombre.text.toString(),
                        BBaseDatosMemoria.formatoFecha.parse(fecha_inaguracion.text.toString()),
                        (porcentaje_personas_satisfechas.text.toString()).toDouble(),
                        (cantidad_empleados.text.toString()).toInt(),
                        BBaseDatosMemoria.arregloBCarro)
                    )

                }

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
                irActividad(ViewConcesionario::class.java)

                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }



/*
    fun agregarConcesionario(adaptador: ArrayAdapter<BConcesionario>){
        BBaseDatosMemoria.arregloBConcesionario.add(BConcesionario(
            "Conc5", BBaseDatosMemoria.formatoFecha.parse("02/07/1990"), 34.5,
             104, BBaseDatosMemoria.arregloBCarro
        ))
        adaptador.notifyDataSetChanged() //Para que se cambie en la pantalla
    }*/

    fun agregarConcesionario(concesionario: BConcesionario){
        arreglo.add(concesionario)
        adaptador.notifyDataSetChanged() //Para que se cambie en la pantalla
    }



    fun eliminarConcesionario(position: Int) {
        arreglo.removeAt(position)
        adaptador.notifyDataSetChanged()
    }


    fun irActividad(clase:Class<*>){
        val intent= Intent(this, clase)
        startActivity(intent)
    }



}