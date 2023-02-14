package com.example.outlook

import java.io.Serializable

class Seccion (
    var idseccion: Int,
    var nameseccion: String,
    //var mensajes: ArrayList<Correo>
): Serializable
{
    override fun toString(): String {
        return " ${idseccion}  - ${nameseccion}"
    }
}