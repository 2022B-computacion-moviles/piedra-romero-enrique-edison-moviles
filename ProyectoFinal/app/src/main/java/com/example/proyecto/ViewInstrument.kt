package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.entity.Instrument
import com.example.proyecto.data.firebase.FirebaseGlobal
import com.example.proyecto.sesion.Sesion
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

        //Carrito
        val aniadirCarrito = findViewById<Button>(R.id.btn_add_carrito)
        aniadirCarrito.setOnClickListener {
            FirebaseGlobal.firebaseCarrito.create(
                Carrito(
                    0,
                    instrumentSelected.codeInstrument,
                1,
                1*instrumentSelected.precio)
            )
            Toast.makeText(this, "Añadido al carrito", Toast.LENGTH_SHORT).show()
        }


        //Header & Auth
        val btnHeader = findViewById<Button>(R.id.header_button)
        val logoHeader = findViewById<ImageView>(R.id.header_logo)
        Sesion.sesionCurrent(this,btnHeader)
        Sesion.menuSesion(this,btnHeader)
        Sesion.headerLogo(this, logoHeader)

    }


}