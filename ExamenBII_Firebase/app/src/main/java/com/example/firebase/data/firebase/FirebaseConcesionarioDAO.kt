package com.example.firebase.data.firebase

import com.example.firebase.data.dao.ConcesionarioDAO
import com.example.firebase.data.entity.Concesionario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class FirebaseConcesionarioDAO: ConcesionarioDAO {

    private val db = Firebase.firestore
    private val concesionariosCollectionReference = db.collection("concesionarios")

    override fun getAllConcesionarios(onSuccess: (ArrayList<Concesionario>) -> Unit) {
        concesionariosCollectionReference
            .get()
            .addOnSuccessListener { documents ->
                val concesionarios = ArrayList<Concesionario>()


                for (document in documents) {
                    concesionarios.add(
                        Concesionario(
                            code = document.id.split("/").last().toInt(),
                            nombre = document.getString("nombre")!!,
                            fecha_inaguracion = LocalDate.parse(document.getString("fecha_inaguracion")!!),
                            porcentaje_personas_satisfechas = document.getDouble("porcentaje_personas_satisfechas")!!,
                            cantidad_empleados =  document.getDouble("cantidad_empleados")!!.toInt()
                        )

                    )
                }

                onSuccess(concesionarios)
            }
    }

    override fun create(entity: Concesionario) {
        val concesionario = hashMapOf(
            "nombre" to entity.nombre,
            "fecha_inaguracion" to entity.fecha_inaguracion.toString(),
            "porcentaje_personas_satisfechas" to entity.porcentaje_personas_satisfechas,
            "cantidad_empleados" to entity.cantidad_empleados
        )

        concesionariosCollectionReference.document(entity.code.toString()).set(concesionario)
    }

    override fun read(code: Int, onSuccess: (Concesionario) -> Unit) {
        val concesionarioReference = concesionariosCollectionReference.document(code.toString())

        concesionarioReference
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val concesionario = Concesionario(
                        code,
                        document.data!!["nombre"].toString(),
                        LocalDate.parse(document.data!!["fecha_inaguracion"].toString()),
                        document.data!!["porcentaje_personas_satisfechas"].toString().toDouble(),
                        document.data!!["cantidad_empleados"].toString().toDouble().toInt()
                    )

                    onSuccess(concesionario)
                }
            }
    }

    override fun update(entity: Concesionario) {
        val concesionario = hashMapOf(
            "nombre" to entity.nombre,
            "fecha_inaguracion" to entity.fecha_inaguracion.toString(),
            "porcentaje_personas_satisfechas" to entity.porcentaje_personas_satisfechas,
            "cantidad_empleados" to entity.cantidad_empleados
        )

        concesionariosCollectionReference.document(entity.code.toString()).set(concesionario)
    }

    override fun delete(code: Int, onSuccess: (Unit) -> Unit) {
        val concesionarioReference = concesionariosCollectionReference.document(code.toString())

        concesionarioReference.delete().addOnSuccessListener {
            onSuccess(Unit)
        }
    }

    override fun getNextCode(onSuccess: (Int) -> Unit) {
        var nextCode = 0

        getAllConcesionarios { documents ->
            for (document in documents) {
                if (document.code > nextCode)
                    nextCode = document.code
            }

            onSuccess(nextCode + 1)
        }
    }

}