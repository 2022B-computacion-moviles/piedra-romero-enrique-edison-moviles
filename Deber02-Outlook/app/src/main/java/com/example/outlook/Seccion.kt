package com.example.outlook

import java.io.Serializable

class Seccion (
    var name_seccion: String,
    var mensajes: ArrayList<Correo>
): Serializable
{
    override fun toString(): String {
        return "${name_seccion} - ${mensajes} "
    }
}