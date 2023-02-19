package com.example.outlook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InsertCorreo : AppCompatActivity() {
    private lateinit var database: AppDataBase
    lateinit var seccion: Seccion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_correo)

        database =AppDataBase.getInstance(this)

        val emisor = findViewById<EditText>(R.id.insert_correo_emisor)
        val receptor = findViewById<EditText>(R.id.insert_correo_receptor)
        val mensaje = findViewById<EditText>(R.id.insert_correo_mensaje)
        val bton_enviar = findViewById<Button>(R.id.insert_correo_btn_enviar)
        seccion = intent.getSerializableExtra("seccion") as Seccion

        bton_enviar.setOnClickListener{
            database.correoDao.insert(Correo(emisor = emisor.text.toString(), receptor = receptor.text.toString(), mensaje = mensaje.text.toString(), idseccion = seccion.id))
            Toast.makeText(this, "Correo Ingresado correctamente", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ViewSeccion::class.java)
            intent.putExtra("seccion", seccion)
            startActivity(intent)
        }





    }

    fun irActividad(clase:Class<*>){
        val intent= Intent(this, clase)
        startActivity(intent)
    }
}