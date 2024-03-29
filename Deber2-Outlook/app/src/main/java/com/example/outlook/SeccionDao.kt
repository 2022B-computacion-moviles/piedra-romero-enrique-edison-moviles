package com.example.outlook

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SeccionDao {
    @Query("SELECT * FROM seccion")
    fun getAll(): List<Seccion>

    @Query("SELECT * FROM seccion WHERE nameseccion = :nameseccion")
    fun get(nameseccion: String): Seccion

    @Insert
    fun insert(secciones: Seccion)

    @Update
    fun update(seccion: Seccion)

    @Delete
    fun delete(seccion: Seccion)

}