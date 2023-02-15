package com.example.outlook

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Seccion (
    @PrimaryKey
    var nameseccion: String
): Serializable
{
    override fun toString(): String {
        return "${nameseccion}"
    }
}