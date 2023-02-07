package com.example.concesionario

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarCarro : AppCompatActivity() {
    var idItemSeleccionado=0
    var idCarroSeleccionado=0

    private lateinit var concesionario: BConcesionario

    private lateinit var carro: BCarro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_carro)

        concesionario = intent.getSerializableExtra("concesionario") as BConcesionario

        idItemSeleccionado= intent.getIntExtra("idItemSeleccionado",0)
        idCarroSeleccionado= intent.getIntExtra("idCarroSeleccionado",0)

        //concesionario=BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]

        carro= concesionario.carros[idCarroSeleccionado]

        val marca = findViewById<EditText>(R.id.input_car_newmarca)
        marca.setText(carro.marca)
        //CAMBIO LA FECHA
        val fecha_elaboracion = findViewById<TextView>(R.id.input_car_newfecha)
        val fechaParseada = BBaseDatosMemoria.formatoEntrada.parse(carro.fecha_elaboracion.toString())
        val fechaSalida = BBaseDatosMemoria.formatoSalida.format(fechaParseada)
        fecha_elaboracion.setText(fechaSalida)
        val precio = findViewById<TextView>(R.id.input_car_newprecio)
        precio.setText(carro.precio.toString())
        val color_subjetivo = findViewById<TextView>(R.id.input_car_newcolor)
        color_subjetivo.setText(carro.color_subjetivo.toString())
        val meses_plazo_pagar = findViewById<TextView>(R.id.input_car_newmeses)
        meses_plazo_pagar.setText(carro.meses_plazo_pagar.toString())

        val botonEditarConcesionario=findViewById<Button>(R.id.btn_ed_carro)
        botonEditarConcesionario
            .setOnClickListener {
                if (TextUtils.isEmpty(marca.text) || TextUtils.isEmpty(fecha_elaboracion.text) || TextUtils.isEmpty(precio.text) || TextUtils.isEmpty(color_subjetivo.text) || TextUtils.isEmpty(meses_plazo_pagar.text)) {
                    //No pasa nada
                }
                else{
                    //Parametros completos
                    editarCarro(BCarro(
                        marca.text.toString(),
                        BBaseDatosMemoria.formatoFecha.parse(fecha_elaboracion.text.toString()),
                        (precio.text.toString()).toDouble(),
                        color_subjetivo.text.toString()=="true",
                        (meses_plazo_pagar.text.toString()).toInt())
                    )



                }
            }
    }


    fun editarCarro(carro: BCarro){
        //BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado].carros[idCarroSeleccionado]=carro
        //var concesionario_aux= BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]
        concesionario.carros[idCarroSeleccionado]=carro

        //concesionario.carros[idCarroSeleccionado] = carro
        BBaseDatosMemoria.arregloBConcesionario[idItemSeleccionado]=concesionario
        BBaseDatosMemoria.adaptadorCarros.notifyDataSetChanged()
        BBaseDatosMemoria.adaptador.notifyDataSetChanged()


        AlertDialog.Builder(this)
            .setTitle("Modificado c:${idItemSeleccionado} car:${idCarroSeleccionado}")
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