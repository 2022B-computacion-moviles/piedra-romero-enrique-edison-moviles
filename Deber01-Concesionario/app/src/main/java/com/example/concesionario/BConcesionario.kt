package com.example.concesionario
import java.util.Date
class BConcesionario
    (
    var nombre: String,
    var fecha_inaguracion: Date,
    //var porcentaje_personas_satisfechas: Double,
    //var exporta_internacionalmente: Boolean,
    var cantidad_empleados: Int,
    //var carros: ArrayList<BCarro>
    )
{
    override fun toString(): String {
        return "${nombre}  - ${fecha_inaguracion} - ${cantidad_empleados} "
    }

}