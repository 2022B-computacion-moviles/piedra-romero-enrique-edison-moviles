package com.example.proyecto.data.firebase

import com.example.proyecto.data.entity.Instrument
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class FirebaseInstruments {
    private val db = Firebase.firestore
    private val collectionReference = db.collection("instrumentos")


    fun getAllInstruments(callback: (ArrayList<Instrument>) -> Unit) {
        collectionReference.get()
            .addOnSuccessListener { documents ->
                val instruments = ArrayList<Instrument>()
                for (document in documents) {
                    instruments.add(
                        Instrument(
                            codeInstrument = document.id.split("/").last().toInt(),
                            nombre = document.getString("nombre")!!,
                            tipo = document.getString("tipo")!!,
                            descripcion = document.getString("descripcion")!!,
                            stock =  document.getDouble("stock")!!.toInt(),
                            precio = document.getDouble("precio")!!,
                            img = document.getString("img")!!,
                        )
                    )
                }
                callback(instruments)
            }
    }

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

    fun read(code: Int, callback: (Instrument) -> Unit) {
        val instrumentReference = collectionReference.document(code.toString())

        instrumentReference
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val instrument=Instrument(
                        codeInstrument = code,
                        nombre = document.getString("nombre")!!,
                        tipo = document.getString("tipo")!!,
                        descripcion = document.getString("descripcion")!!,
                        stock =  document.getDouble("stock")!!.toInt(),
                        precio = document.getDouble("precio")!!,
                        img = document.getString("img")!!,
                    )
                    callback(instrument)
                }

            }
    }




}