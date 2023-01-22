package com.example.movcomp_eepr

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moviles", //Nombre del archivo de la BDD (moviles.sqlite)
    null,
    1
)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador=
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean{
        val basedatosEscritura=writableDatabase
        val valoresAGuardar=ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)


        val resultadoGuardar=basedatosEscritura
            .insert(
                "ENTRENADOR", //Tabla
                null,
                valoresAGuardar //valores
            )

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

    fun consultarEntrenadorPorID(id:Int):BEntrenador{
        val baseDatosLectura=readableDatabase
        val scriptConsultarUsuario="SELECT * FROM ENTRENADOR WHERE ID=${id}"  //Para evitar inyection SQL
        val resultadoConsultaLectura=baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            arrayOf(
                id.toString()
            )
        )
        val existeUsuario=resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado=BEntrenador(0, "", "")
        //LOGICA OBTENER USUARIO
        do{
            val id=resultadoConsultaLectura.getInt(0) //indice representa cada dato
            val nombre=resultadoConsultaLectura.getString(1)
            val descripcion=resultadoConsultaLectura.getString(2)
            if(id!=null){
                usuarioEncontrado.id=id
                usuarioEncontrado.nombre=nombre
                usuarioEncontrado.descripcion=descripcion
            }
        }while(resultadoConsultaLectura.moveToNext())

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

}