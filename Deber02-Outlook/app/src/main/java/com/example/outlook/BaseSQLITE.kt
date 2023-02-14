package com.example.outlook

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseSQLITE(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "outlook", //Nombre del archivo de la BDD (moviles.sqlite)
    null,
    1
)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaSeccion = "CREATE TABLE SECCION (" +
                "nameseccion VARCHAR(50)" +
                ")"
        db?.execSQL(scriptSQLCrearTablaSeccion)

        val scriptSQLCrearTablaCorreo = "CREATE TABLE CORREO (" +
                "idcorreo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nameseccion VARCHAR(50), " +
                "emisor VARCHAR(50), " +
                "receptor VARCHAR(50), " +
                "mensaje VARCHAR(50), " +
                "FOREIGN KEY(nameseccion) REFERENCES SECCION(nameseccion)" +
                ")"
        db?.execSQL(scriptSQLCrearTablaCorreo)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearSeccion(nameseccion: String): Boolean{
        val basedatosEscritura=writableDatabase
        val valoresAGuardar=ContentValues()
        valoresAGuardar.put("nameseccion", nameseccion)

        val resultadoGuardar=basedatosEscritura.insert("SECCION", null, valoresAGuardar)
        basedatosEscritura.close()
        return  if(resultadoGuardar.toInt()==-1) false else true
    }







    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura=writableDatabase
        val resultadoEliminacion=conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return  if(resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        idActualizar:Int
    ): Boolean{
        val conexionEscritura=writableDatabase
        val valoresAActualizar=ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val resultadoActualizacion=conexionEscritura
            .update(
                "ENTRENADOR",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return  if(resultadoActualizacion.toInt()==-1) false else true
    }


}