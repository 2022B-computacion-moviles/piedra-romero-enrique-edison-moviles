package com.example.concesionario
import java.io.Serializable
import java.util.Date
class BConcesionario
    (
    var nombre: String,
    var fecha_inaguracion: Date,
    var porcentaje_personas_satisfechas: Double,
    var cantidad_empleados: Int,
    var carros: ArrayList<BCarro>
    ): Serializable
{
    override fun toString(): String {
        return "${nombre} - ${fecha_inaguracion} - ${porcentaje_personas_satisfechas} - ${cantidad_empleados} - ${carros} "
    }

}