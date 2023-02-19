package com.example.outlook

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CorreoDao {
    @Query("SELECT * FROM correo")
    fun getAll(): List<Correo>

    @Query("SELECT * FROM correo WHERE idseccion = :idseccion")
    fun get(idseccion: Int): List<Correo>

    @Insert
    fun insert(correo: Correo)

    @Update
    fun update(correo: Correo)

    @Delete
    fun delete(correo: Correo)

}