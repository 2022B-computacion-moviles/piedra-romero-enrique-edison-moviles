package com.example.proyecto.data.firebase

import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.entity.Instrument
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class FirebaseCarrito {
    private val db = Firebase.firestore
    private val collectionReference = db.collection("clientes")


    fun getAllCarritos(callback: (ArrayList<Carrito>) -> Unit) {
        collectionReference.get()
            .addOnSuccessListener { documents ->
                val instruments = ArrayList<Carrito>()
                for (document in documents) {
                    instruments.add(
                        Carrito(
                            codeCarrito=document.id.split("/").last().toInt(),
                            codeInstrument =document.getDouble("codeInstrument")!!.toInt(),
                            cantidad = document.getDouble("cantidad")!!.toInt(),
                            total = document.getDouble("total")!!
                        )
                    )
                }
                callback(instruments)
            }
    }

    fun create(carrito: Carrito, email: String) {

        val entity = hashMapOf(
            "codeInstrument" to carrito.codeInstrument,
            "cantidad" to carrito.cantidad,
            "total" to carrito.total
        )
        //collectionReference.document(carrito.codeCarrito.toString()).set(entity)

        collectionReference
            .document(email)
            .collection("carrito")
            .document(carrito.codeCarrito.toString()).set(entity)
    }


}