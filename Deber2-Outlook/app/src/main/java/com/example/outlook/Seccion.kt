package com.example.outlook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "seccion")
data class Seccion (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int=0,

    @ColumnInfo(name = "nameseccion")
    var nameseccion: String
): Serializable
{
    override fun toString(): String {
        return "${nameseccion}"
    }
}