package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.proyecto.data.entity.Instrument
import com.google.firebase.auth.FirebaseAuth

class ViewInstrument : AppCompatActivity() {
    lateinit var instrumentSelected: Instrument

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_instrument)


        instrumentSelected= intent.getSerializableExtra("instrumentSelected") as Instrument

        val codeTextView = findViewById<TextView>(R.id.view_instrument_code)
        val nombreTextView = findViewById<TextView>(R.id.view_instrument_nombre)
        val tipoTextView = findViewById<TextView>(R.id.view_instrument_tipo)
        val descripcionTextView = findViewById<TextView>(R.id.view_instrument_descripcion)
        val stockTextView = findViewById<TextView>(R.id.view_instrument_stock)
        val precioTextView = findViewById<TextView>(R.id.view_instrument_precio)
        val photoImagenView = findViewById<ImageView>(R.id.view_instrument_img)


        codeTextView.text = instrumentSelected.codeInstrument.toString()
        nombreTextView.text = instrumentSelected.nombre
        tipoTextView.text = instrumentSelected.tipo
        descripcionTextView.text = instrumentSelected.descripcion
        stockTextView.text = instrumentSelected.stock.toString()
        precioTextView.text = "$" +instrumentSelected.precio.toString()
        Glide.with(photoImagenView.context).load(instrumentSelected.img).into(photoImagenView)

        //SESIÓN
        sesionCurrent()


        val aniadirCarrito = findViewById<Button>(R.id.btn_add_carrito)
        aniadirCarrito.setOnClickListener {


        }


    }

    private fun sesionCurrent(){
        val emailText = findViewById<TextView>(R.id.view_instrument_email)
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email
        if (email != null) {
            emailText.text = email
        }
    }
}