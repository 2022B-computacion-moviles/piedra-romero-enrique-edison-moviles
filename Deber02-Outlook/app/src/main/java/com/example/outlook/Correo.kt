package com.example.outlook

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class Correo    (
    var emisor: String,
    var receptor: String,
    var mensaje: String,
    //var carros: ArrayList<BCarro>
): Serializable
{
    override fun toString(): String {
        return "${emisor} - ${receptor} - ${mensaje}"
    }
}

/*
class Correo    (
    var idcorreo: Int,
    var seccion: Int,
    var emisor: String,
    var receptor: String,
    var mensaje: String


): Serializable
{
    override fun toString(): String {
        return "${emisor} - ${receptor} - ${mensaje}"
    }
}*/


