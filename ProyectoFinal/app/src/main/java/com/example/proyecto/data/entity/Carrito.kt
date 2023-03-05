package com.example.proyecto.data.entity

import java.io.Serializable

class Carrito(
    val codeCarrito: Int,
    val codeInstrument: Int,

    var cantidad: Int,
    var total: Double
): Serializable