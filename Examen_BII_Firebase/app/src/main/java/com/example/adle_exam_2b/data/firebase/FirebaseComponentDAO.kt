package com.example.adle_exam_2b.data.firebase

import com.example.adle_exam_2b.data.dao.ComponentDAO
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.entity.ComponentEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class FirebaseComponentDAO: ComponentDAO {

    private val db = Firebase.firestore
    private val devicesCollectionReference = db.collection("devices")

    override fun getAllComponentsByDeviceCode(
        deviceCode: Int,
        onSuccess: (ArrayList<ComponentEntity>) -> Unit
    ) {
        devicesCollectionReference
            .document(deviceCode.toString())
            .collection("components")
            .get()
            .addOnSuccessListener { documents ->
                val components = ArrayList<ComponentEntity>()

                for (document in documents) {
                    components.add(
                        ComponentEntity(
                            code = document.id.split("/").last().toInt(),
                            deviceCode = deviceCode,
                            marca = document.getString("marca")!!,
                            fecha_elaboracion = LocalDate.parse(document.getString("fecha_elaboracion")!!),
                            precio = document.getDouble("precio")!!,
                            color_subjetivo = document.getBoolean("color_subjetivo")!!,
                            meses_plazo_pagar = document.getDouble("meses_plazo_pagar")!!.toInt()
                        )
                    )
                }

                onSuccess(components)
            }
    }

    override fun create(entity: ComponentEntity) {
        val component = hashMapOf(
            "marca" to entity.marca,
            "fecha_elaboracion" to  entity.fecha_elaboracion.toString(),
            "precio" to entity.precio,
            "color_subjetivo" to entity.color_subjetivo,
            "meses_plazo_pagar" to entity.meses_plazo_pagar,
        )

        devicesCollectionReference
            .document(entity.deviceCode.toString())
            .collection("components")
            .document(entity.code.toString()).set(component)
    }

    override fun read(code: Int, onSuccess: (ComponentEntity) -> Unit) {
        DAOFactory.factory.getDeviceDAO().getAllDevices { devices ->
            for (device in devices) {
                val db = Firebase.firestore
                val componentsCollectionReference = db.collection(
                    "devices/${device.code}/components"
                )

                componentsCollectionReference
                    .get()
                    .addOnSuccessListener { components ->
                        for (component in components) {
                            if (component.id.toInt() == code) {
                                onSuccess(
                                    ComponentEntity(
                                        component.id.toInt(),
                                        device.code,
                                        component.getString("marca")!!,
                                        LocalDate.parse(component.data!!["fecha_elaboracion"].toString()),
                                        component.getDouble("precio")!!,
                                        component.getBoolean("color_subjetivo")!!,
                                        component.getString("meses_plazo_pagar")!!.toInt(),
                                    )
                                )
                            }
                        }
                    }
            }
        }
    }

    override fun update(entity: ComponentEntity) {
        val component = hashMapOf(
            "marca" to entity.marca,
            "fecha_elaboracion" to  entity.fecha_elaboracion.toString(),
            "precio" to entity.precio,
            "color_subjetivo" to entity.color_subjetivo,
            "meses_plazo_pagar" to entity.meses_plazo_pagar,
        )

        devicesCollectionReference
            .document(entity.deviceCode.toString())
            .collection("components")
            .document(entity.code.toString()).set(component)
    }

    override fun delete(code: Int, onSuccess: (Unit) -> Unit) {
        DAOFactory.factory.getDeviceDAO().getAllDevices { devices ->
            for (device in devices) {
                val db = Firebase.firestore
                val componentsCollectionReference = db.collection(
                    "devices/${device.code}/components"
                )

                componentsCollectionReference
                    .get()
                    .addOnSuccessListener { components ->
                        for (component in components) {
                            if (component.id.toInt() == code) {
                                val componentReference = componentsCollectionReference
                                    .document(code.toString())

                                componentReference.delete().addOnSuccessListener {
                                    onSuccess(Unit)
                                }
                            }
                        }
                    }
            }
        }
    }

    override fun getNextCode(onSuccess: (Int) -> Unit) {
        var nextCode = 0

        DAOFactory.factory.getDeviceDAO().getAllDevices { devices ->
            for (device in devices) {
                val db = Firebase.firestore
                val componentsCollectionReference = db.collection(
                    "devices/${device.code}/components"
                )

                componentsCollectionReference
                    .get()
                    .addOnSuccessListener { components ->
                        for (component in components) {
                            if (component.id.toInt() > nextCode)
                                nextCode = component.id.toInt()
                        }

                        onSuccess(nextCode + 1)
                    }
            }
        }
    }

}