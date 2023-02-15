package com.example.outlook

import androidx.room.*


@Dao
interface SeccionDAO {
    @Query("SELECT * FROM Seccion")
    fun getAll(): List<Seccion>

    @Query("SELECT * FROM Seccion WHERE nameseccion = :nameseccion")
    fun get(nameseccion: String): Seccion

    @Insert
    fun insert(secciones: List<Seccion>)

    @Update
    fun update(seccion: Seccion)

    @Delete
    fun delete(seccion: Seccion)

}