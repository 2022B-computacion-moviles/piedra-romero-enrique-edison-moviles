package com.example.adle_exam_2b.data.entity

import java.time.LocalDate


class DeviceEntity(
    val code: Int, //La id de clase principal (Concesionario)

    var nombre: String,
    var fecha_inaguracion: LocalDate,
    var porcentaje_personas_satisfechas: Double,
    var cantidad_empleados: Int
)