package com.example.outlook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ECrudSeccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_seccion)

        //Boton Crear
        val botonCrearBDD = findViewById<Button>(R.id.crud_seccion_btn_crear)
        botonCrearBDD
            .setOnClickListener {
                val seccion = findViewById<EditText>(R.id.crud_seccion_text_seccion)
                EBaseDeDatos.tablaOutlook!!.crearSeccion(
                    seccion.text.toString()
                )
            }


    }
}