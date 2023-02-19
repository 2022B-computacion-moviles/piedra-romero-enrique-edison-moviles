package com.example.outlook

import androidx.room.*
import java.io.Serializable

@Entity(
    tableName = "correo",
    foreignKeys = [
        ForeignKey(
            entity = Seccion::class,
            parentColumns = ["id"],
            childColumns = ["idseccion"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("idseccion")]
)
data class Correo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idcorreo")
    var idcorreo: Int = 0,
    @ColumnInfo(name = "emisor")
    var emisor: String,
    @ColumnInfo(name = "receptor")
    var receptor: String,
    @ColumnInfo(name = "mensaje")
    var mensaje: String,
    @ColumnInfo(name = "idseccion")
    var idseccion: Int
): Serializable {
    override fun toString(): String {
        return "${emisor}"
    }
}