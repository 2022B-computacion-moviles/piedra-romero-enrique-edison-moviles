package com.example.concesionario

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarConcesionario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_concesionario)

        val concesionario = intent.getSerializableExtra("concesionario") as BConcesionario
        val idItemSeleccionado= intent.getIntExtra("idItemSeleccionado",0)

        val nombre = findViewById<EditText>(R.id.input_conc_newnombre)
        nombre.setText(concesionario.nombre)
        //CAMBIO LA FECHA
        val fecha_inaguracion = findViewById<TextView>(R.id.input_conc_newfecha)
        val fechaParseada = BBaseDatosMemoria.formatoEntrada.parse(concesionario.fecha_inaguracion.toString())
        val fechaSalida = BBaseDatosMemoria.formatoSalida.format(fechaParseada)
        fecha_inaguracion.setText(fechaSalida)

        val porcentaje_personas_satisfechas = findViewById<TextView>(R.id.input_conc_newporcentaje)
        porcentaje_personas_satisfechas.setText(concesionario.porcentaje_personas_satisfechas.toString())
        val cantidad_empleados = findViewById<TextView>(R.id.input_conc_newempleados)
        cantidad_empleados.setText(concesionario.cantidad_empleados.toString())

        val botonEditarConcesionario=findViewById<Button>(R.id.btn_ed_concesionario)
        botonEditarConcesionario
            .setOnClickListener {


                if (TextUtils.isEmpty(nombre.text) || TextUtils.isEmpty(fecha_inaguracion.text) || TextUtils.isEmpty(porcentaje_personas_satisfechas.text) || TextUtils.isEmpty(cantidad_empleados.text)) {
                    //No pasa nada
                }
                else{
                    //Parametros completos
                    editarConcesionario(BConcesionario(
                        nombre.text.toString(),
                        BBaseDatosMemoria.formatoFecha.parse(fecha_inaguracion.text.toString()),
                        (porcentaje_personas_satisfechas.text.toString()).toDouble(),
                        (cantidad_empleados.text.toString()).toInt(),
                        BBaseDatosMemoria.arregloBCarro),idItemSeleccionado
                    )



                }

            }


    }

    fun editarConcesionario(concesionario: BConcesionario, idItemSeleccionado: Int){
        BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]=concesionario
        BBaseDatosMemoria.adaptador.notifyDataSetChanged()

        AlertDialog.Builder(this)
            .setTitle("Modificado")
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