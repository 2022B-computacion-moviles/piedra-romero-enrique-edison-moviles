package com.example.proyecto.data.entity

import java.io.Serializable
import java.time.LocalDate

class Compra (
    val codeCompra: Int,
    val codeInstrument: Int,

    var fecha: String,
    var cantidad: Int,
    var total: Double
): Serializable