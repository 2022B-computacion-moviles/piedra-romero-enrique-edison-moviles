package com.example.outlook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ViewSeccion : AppCompatActivity() {
    lateinit var seccion: Seccion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_seccion)

        seccion = intent.getSerializableExtra("seccion") as Seccion
        val nombre = findViewById<TextView>(R.id.view_seccion_name)
        nombre.text=seccion.nameseccion

    }
}