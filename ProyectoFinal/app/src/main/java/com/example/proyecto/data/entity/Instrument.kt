package com.example.proyecto.data.entity

import java.io.Serializable

class Instrument(
    val codeInstrument: Int,

    var nombre: String,
    var tipo: String,
    var descripcion: String,
    var stock: Int,
    var precio: Double,
    var img: String
): Serializable