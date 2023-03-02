package com.example.adle_exam_2b.data.entity

import java.time.LocalDate

class ComponentEntity(
    val code: Int, //La id de clase secundaria (carro)
    val deviceCode: Int, //La id de clase principal (Concesionario)

    var marca: String,
    var fecha_elaboracion: LocalDate,
    var precio: Double,
    var color_subjetivo: Boolean,
    var meses_plazo_pagar: Int

)