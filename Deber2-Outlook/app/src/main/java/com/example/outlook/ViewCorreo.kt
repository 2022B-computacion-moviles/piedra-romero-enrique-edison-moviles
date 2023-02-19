package com.example.outlook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ViewCorreo : AppCompatActivity() {
    lateinit var correo: Correo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_correo)


        correo = intent.getSerializableExtra("correo") as Correo
        val emisor = findViewById<TextView>(R.id.view_correo_emisor)
        emisor.text=correo.emisor
        val receptor = findViewById<TextView>(R.id.view_correo_receptor)
        receptor.text=correo.receptor
        val mensaje = findViewById<TextView>(R.id.view_correo_mensaje)
        mensaje.text=correo.mensaje


    }
}