package com.example.proyecto.data.firebase

import com.example.proyecto.data.entity.Instrument
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseInstruments {
    private val db = Firebase.firestore
    private val collectionReference = db.collection("instrumentos")

    fun create(instrument: Instrument) {
        val entity = hashMapOf(
            "nombre" to instrument.nombre,
            "tipo" to instrument.tipo,
            "descripcion" to instrument.descripcion,
            "stock" to instrument.stock,
            "precio" to instrument.precio,
            "img" to instrument.img
        )
        collectionReference.document(instrument.codeInstrument.toString()).set(entity)
    }
}