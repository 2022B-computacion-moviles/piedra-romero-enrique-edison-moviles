package com.example.adle_exam_2b.data.firebase

import com.example.adle_exam_2b.data.dao.DeviceDAO
import com.example.adle_exam_2b.data.entity.DeviceEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class FirebaseDeviceDAO: DeviceDAO {

    private val db = Firebase.firestore
    private val devicesCollectionReference = db.collection("devices")

    override fun getAllDevices(onSuccess: (ArrayList<DeviceEntity>) -> Unit) {
        devicesCollectionReference
            .get()
            .addOnSuccessListener { documents ->
                val devices = ArrayList<DeviceEntity>()

                for (document in documents) {
                    devices.add(
                        DeviceEntity(
                            code = document.id.split("/").last().toInt(),
                            name = document.getString("name")!!,
                            category = document.getString("category")!!,
                            releaseDate = LocalDate.parse(document.getString("release_date")!!),
                            price = document.getDouble("price")!!
                        )
                    )
                }

                onSuccess(devices)
            }
    }

    override fun create(entity: DeviceEntity) {
        val device = hashMapOf(
            "name" to entity.name,
            "category" to entity.category,
            "release_date" to entity.releaseDate.toString(),
            "price" to entity.price
        )

        devicesCollectionReference.document(entity.code.toString()).set(device)
    }

    override fun read(code: Int, onSuccess: (DeviceEntity) -> Unit) {
        val deviceReference = devicesCollectionReference.document(code.toString())

        deviceReference
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val device = DeviceEntity(
                        code,
                        document.data!!["name"].toString(),
                        document.data!!["category"].toString(),
                        LocalDate.parse(document.data!!["release_date"].toString()),
                        document.data!!["price"].toString().toDouble()
                    )

                    onSuccess(device)
                }
            }
    }

    override fun update(entity: DeviceEntity) {
        val device = hashMapOf(
            "name" to entity.name,
            "category" to entity.category,
            "release_date" to entity.releaseDate.toString(),
            "price" to entity.price
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

        getAllDevices { devices ->
            for (device in devices) {
                if (device.code > nextCode)
                    nextCode = device.code
            }

            onSuccess(nextCode + 1)
        }
    }

}