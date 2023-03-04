package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.proyecto.data.entity.Instrument

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

    }
}