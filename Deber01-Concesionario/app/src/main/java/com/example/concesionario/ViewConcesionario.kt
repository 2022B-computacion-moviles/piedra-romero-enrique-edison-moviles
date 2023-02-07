package com.example.concesionario

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class ViewConcesionario : AppCompatActivity() {
    var idCarroSeleccionado=0
    var idItemSeleccionado=0


    lateinit var concesionario: BConcesionario
    val arregloConcesionario:ArrayList<BConcesionario> =BBaseDatosMemoria.arregloBConcesionario
    //lateinit var arregloCarros:ArrayList<BCarro>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_concesionario)

        //concesionario = intent.getSerializableExtra("concesionario") as BConcesionario


        idItemSeleccionado= intent.getIntExtra("idItemSeleccionado",0)
        //concesionario=BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]
        concesionario=arregloConcesionario[idItemSeleccionado]


        val actionBar = supportActionBar
        actionBar?.title = concesionario.nombre

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
        BBaseDatosMemoria.adaptadorCarros= ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloConcesionario[idItemSeleccionado].carros)
        listviewcarros.adapter=BBaseDatosMemoria.adaptadorCarros

        BBaseDatosMemoria.adaptadorCarros.notifyDataSetChanged()
        BBaseDatosMemoria.adaptador.notifyDataSetChanged()

        val botonAniadirListView=findViewById<Button>(R.id.btn_add_car)
        botonAniadirListView
            .setOnClickListener {
                //agregarConcesionario(adaptador)
                val marca = findViewById<EditText>(R.id.input_car_marca)
                val fecha_elaboracion = findViewById<EditText>(R.id.input_car_fecha)
                val precio = findViewById<TextView>(R.id.input_car_precio)
                val color_subjetivo = findViewById<TextView>(R.id.input_car_color)
                val meses_plazo_pagar = findViewById<TextView>(R.id.input_car_meses)


                if (TextUtils.isEmpty(marca.text) || TextUtils.isEmpty(fecha_elaboracion.text) || TextUtils.isEmpty(precio.text) || TextUtils.isEmpty(color_subjetivo.text) || TextUtils.isEmpty(meses_plazo_pagar.text)) {
                    //No pasa nada
                }
                else{
                    //Parametros completos
                    agregarCarro(BCarro(
                        marca.text.toString(),
                        BBaseDatosMemoria.formatoFecha.parse(fecha_elaboracion.text.toString()),
                        (precio.text.toString()).toDouble(),
                        color_subjetivo.text.toString()=="true",
                        (meses_plazo_pagar.text.toString()).toInt())
                    )

                }

            }

        registerForContextMenu(listviewcarros)

    }

    //Se ejecuta cuando la actividad sea visible para el usuario
    override fun onResume() {
        super.onResume()
        BBaseDatosMemoria.adaptadorCarros.notifyDataSetChanged()
        BBaseDatosMemoria.adaptador.notifyDataSetChanged()
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
                //val carro = arregloCarros[idCarroSeleccionado]
                val concesionario = arregloConcesionario[idItemSeleccionado]
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

    fun agregarCarro(carro: BCarro){
        concesionario.carros.add(carro)
        //arregloCarros.add(carro)
        //concesionario.carros=arregloCarros
        BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]=concesionario
        BBaseDatosMemoria.adaptadorCarros.notifyDataSetChanged()
        BBaseDatosMemoria.adaptador.notifyDataSetChanged()

        AlertDialog.Builder(this)
            .setTitle("Agregado")
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

    fun eliminarCarro(position: Int) {
        concesionario.carros.removeAt(position)
        //arregloCarros.removeAt(position)
        //concesionario.carros=arregloCarros

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