package com.example.concesionario

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class ViewConcesionario : AppCompatActivity() {
    var idCarroSeleccionado=0
    var idItemSeleccionado=0


    private lateinit var concesionario: BConcesionario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_concesionario)

        concesionario = intent.getSerializableExtra("concesionario") as BConcesionario
        idItemSeleccionado= intent.getIntExtra("idItemSeleccionado",0)

        val nombre = findViewById<TextView>(R.id.vc_nombre)
        nombre.text=concesionario.nombre
        val fecha_inaguracion = findViewById<TextView>(R.id.vc_fecha)
        fecha_inaguracion.text=concesionario.fecha_inaguracion.toString()
        val porcentaje_personas_satisfechas = findViewById<TextView>(R.id.vc_porcentaje)
        porcentaje_personas_satisfechas.text=concesionario.porcentaje_personas_satisfechas.toString()
        val cantidad_empleados = findViewById<TextView>(R.id.vc_empleados)
        cantidad_empleados.text=concesionario.cantidad_empleados.toString()
        //lista carros
        val listviewcarros=findViewById<ListView>(R.id.vc_lv_carros)
        BBaseDatosMemoria.adaptadorCarros= ArrayAdapter(this, android.R.layout.simple_list_item_1, concesionario.carros)
        listviewcarros.adapter=BBaseDatosMemoria.adaptadorCarros
        BBaseDatosMemoria.adaptadorCarros.notifyDataSetChanged()

        registerForContextMenu(listviewcarros)

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
        inflater.inflate(R.menu.menucarro, menu)
        //Obtener el id del ArrayListSeleccionado
        val info =menuInfo as AdapterView.AdapterContextMenuInfo
        val id= info.position
        idCarroSeleccionado=id
    }


    //opcion seleccionada del menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.item_btn_car_editar->{
                //val carro = concesionario.carros[idCarroSeleccionado]
                val intent = Intent(this, EditarCarro::class.java)
                //intent.putExtra("carro", carro)
                intent.putExtra("concesionario", concesionario)
                intent.putExtra("idItemSeleccionado", idItemSeleccionado)
                intent.putExtra("idCarroSeleccionado", idCarroSeleccionado)
                startActivity(intent)
                return true
            }
            R.id.item_btn_car_eliminar->{
                eliminarCarro(idCarroSeleccionado)
                return true
            }

            else-> super.onContextItemSelected(item)
        }
    }

    fun eliminarCarro(position: Int) {
        concesionario.carros.removeAt(position)

        BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]=concesionario
        BBaseDatosMemoria.adaptadorCarros.notifyDataSetChanged()
        BBaseDatosMemoria.adaptador.notifyDataSetChanged()

        AlertDialog.Builder(this)
            .setTitle("Eliminado")
            .setMessage("Con Éxito")
            .setPositiveButton("Aceptar") { dialog, which ->
                // acción cuando se presiona Aceptar
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // acción cuando se presiona Cancelar
            }
            .create()
            .show()
    }
}