package com.example.proyecto.data.firebase

import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.entity.Compra
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//UNA COMPRA ES UN POCO DISTINTO DE UN CARRITO PORQUE YA SE PAGA
class FirebaseCompra {
    private val db = Firebase.firestore
    private val collectionReference = db.collection("clientes")


    fun getAllCompras(callback: (ArrayList<Compra>) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        collectionReference
            .document(email.toString())
            .collection("compras")
            .get()
            .addOnSuccessListener { documents ->
                val compras = ArrayList<Compra>()
                for (document in documents) {
                    compras.add(
                        Compra(
                            codeCompra=document.id.split("/").last().toInt(),
                            codeInstrument =document.getDouble("codeInstrument")!!.toInt(),
                            fecha=document.getString("fecha")!!,
                            cantidad = document.getDouble("cantidad")!!.toInt(),
                            total = document.getDouble("total")!!
                        )
                    )
                }
                callback(compras)
            }
    }

    fun create(carrito: Carrito) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        val entity = hashMapOf(
            "codeInstrument" to carrito.codeInstrument,
            "fecha" to getFecha(),
            "cantidad" to carrito.cantidad,
            "total" to carrito.total
        )

        collectionReference
            .document(email.toString())
            .collection("compras")
            .document(getRandomCode().toString()).set(entity)

        //Elimina del carrito
        FirebaseGlobal.firebaseCarrito.delete(carrito.codeCarrito){}
    }


    private fun getRandomCode():Int {
        var identificador = Date().time.toInt()
        if(identificador<0){
            identificador*=-1
        }
        return identificador
    }

    private fun getFecha():String{
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        return currentDate.toString()
    }




}