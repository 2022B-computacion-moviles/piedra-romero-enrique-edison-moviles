package com.example.adle_exam_2b.data.entity

import java.time.LocalDate

class DeviceEntity(
    val code: Int,
    var name: String,
    var category: String,
    var releaseDate: LocalDate,
    var price: Double
)