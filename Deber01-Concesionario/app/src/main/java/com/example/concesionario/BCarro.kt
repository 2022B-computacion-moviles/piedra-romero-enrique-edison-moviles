package com.example.concesionario

import java.io.Serializable
import java.util.Date

class BCarro
    (
    var marca: String,
    var fecha_elaboracion: Date,
    var precio: Double,
    var color_subjetivo: Boolean,
    var meses_plazo_pagar: Int
    ): Serializable
{
    override fun toString(): String {
        return "${marca}  - ${fecha_elaboracion} - ${precio} - ${color_subjetivo} - ${meses_plazo_pagar}"
    }
}