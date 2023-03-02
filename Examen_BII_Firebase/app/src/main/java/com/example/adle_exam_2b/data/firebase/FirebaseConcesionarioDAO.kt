package com.example.adle_exam_2b.data.firebase

import com.example.adle_exam_2b.data.dao.ConcesionarioDAO
import com.example.adle_exam_2b.data.entity.Concesionario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class FirebaseConcesionarioDAO: ConcesionarioDAO {

    private val db = Firebase.firestore
    private val devicesCollectionReference = db.collection("concesionarios")

    override fun getAllConcesionarios(onSuccess: (ArrayList<Concesionario>) -> Unit) {
        devicesCollectionReference
            .get()
            .addOnSuccessListener { documents ->
                val devices = ArrayList<Concesionario>()


                for (document in documents) {
                    devices.add(
                        Concesionario(
                            code = document.id.split("/").last().toInt(),
                            nombre = document.getString("nombre")!!,
                            fecha_inaguracion = LocalDate.parse(document.getString("fecha_inaguracion")!!),
                            porcentaje_personas_satisfechas = document.getDouble("porcentaje_personas_satisfechas")!!,
                            cantidad_empleados =  document.getDouble("cantidad_empleados")!!.toInt()
                        )

                    )
                }

                onSuccess(devices)
            }
    }

    override fun create(entity: Concesionario) {
        val device = hashMapOf(
            "nombre" to entity.nombre,
            "fecha_inaguracion" to entity.fecha_inaguracion.toString(),
            "porcentaje_personas_satisfechas" to entity.porcentaje_personas_satisfechas,
            "cantidad_empleados" to entity.cantidad_empleados
        )

        devicesCollectionReference.document(entity.code.toString()).set(device)
    }

    override fun read(code: Int, onSuccess: (Concesionario) -> Unit) {
        val deviceReference = devicesCollectionReference.document(code.toString())

        deviceReference
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val device = Concesionario(
                        code,
                        document.data!!["nombre"].toString(),
                        LocalDate.parse(document.data!!["fecha_inaguracion"].toString()),
                        document.data!!["porcentaje_personas_satisfechas"].toString().toDouble(),
                        document.data!!["cantidad_empleados"].toString().toDouble().toInt()
                    )

                    onSuccess(device)
                }
            }
    }

    override fun update(entity: Concesionario) {
        val device = hashMapOf(
            "nombre" to entity.nombre,
            "fecha_inaguracion" to entity.fecha_inaguracion.toString(),
            "porcentaje_personas_satisfechas" to entity.porcentaje_personas_satisfechas,
            "cantidad_empleados" to entity.cantidad_empleados
        )

        devicesCollectionReference.document(entity.code.toString()).set(device)
    }

    override fun delete(code: Int, onSuccess: (Unit) -> Unit) {
        val deviceReference = devicesCollectionReference.document(code.toString())

        deviceReference.delete().addOnSuccessListener {
            onSuccess(Unit)
        }
    }

    override fun getNextCode(onSuccess: (Int) -> Unit) {
        var nextCode = 0

        getAllConcesionarios { devices ->
            for (device in devices) {
                if (device.code > nextCode)
                    nextCode = device.code
            }

            onSuccess(nextCode + 1)
        }
    }

}