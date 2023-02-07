package com.example.concesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class ViewConcesionario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_concesionario)

        val concesionario = intent.getSerializableExtra("concesionario") as BConcesionario

        val nombre = findViewById<TextView>(R.id.vc_nombre)
        nombre.text=concesionario.nombre
        val fecha_inaguracion = findViewById<TextView>(R.id.vc_fecha)
        fecha_inaguracion.text=concesionario.fecha_inaguracion.toString()
        val porcentaje_personas_satisfechas = findViewById<TextView>(R.id.vc_porcentaje)
        porcentaje_personas_satisfechas.text=concesionario.porcentaje_personas_satisfechas.toString()
        val cantidad_empleados = findViewById<TextView>(R.id.vc_empleados)
        cantidad_empleados.text=concesionario.cantidad_empleados.toString()
        //lista carros
        val listviewcarros = findViewById<ListView>(R.id.vc_lv_carros)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, concesionario.carros)
        listviewcarros.adapter = adaptador



    }
}