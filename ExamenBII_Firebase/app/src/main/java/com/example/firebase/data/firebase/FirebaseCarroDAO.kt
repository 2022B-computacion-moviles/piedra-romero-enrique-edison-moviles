package com.example.firebase.data.firebase

import com.example.firebase.data.dao.CarroDAO
import com.example.firebase.data.dao.DAOFactory
import com.example.firebase.data.entity.Carro
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class FirebaseCarroDAO: CarroDAO {

    private val db = Firebase.firestore
    private val concesionarioCollectionReference = db.collection("concesionarios")

    override fun getAllCarrosByCodeCar(
        concesionarioCode: Int,
        onSuccess: (ArrayList<Carro>) -> Unit
    ) {
        concesionarioCollectionReference
            .document(concesionarioCode.toString())
            .collection("carros")
            .get()
            .addOnSuccessListener { documents ->
                val carros = ArrayList<Carro>()

                for (document in documents) {
                    carros.add(
                        Carro(
                            code = document.id.split("/").last().toInt(),
                            deviceCode = concesionarioCode,
                            marca = document.getString("marca")!!,
                            fecha_elaboracion = LocalDate.parse(document.getString("fecha_elaboracion")!!),
                            precio = document.getDouble("precio")!!,
                            color_subjetivo = document.getBoolean("color_subjetivo")!!,
                            meses_plazo_pagar = document.getDouble("meses_plazo_pagar")!!.toInt()
                        )
                    )
                }

                onSuccess(carros)
            }
    }

    override fun create(entity: Carro) {
        val carro = hashMapOf(
            "marca" to entity.marca,
            "fecha_elaboracion" to  entity.fecha_elaboracion.toString(),
            "precio" to entity.precio,
            "color_subjetivo" to entity.color_subjetivo,
            "meses_plazo_pagar" to entity.meses_plazo_pagar,
        )

        concesionarioCollectionReference
            .document(entity.deviceCode.toString())
            .collection("carros")
            .document(entity.code.toString()).set(carro)
    }

    override fun read(code: Int, onSuccess: (Carro) -> Unit) {
        DAOFactory.factory.getConcesionarioDAO().getAllConcesionarios { documents ->
            for (document in documents) {
                val db = Firebase.firestore
                val carroCollectionReference = db.collection(
                    "concesionarios/${document.code}/carros"
                )

                carroCollectionReference
                    .get()
                    .addOnSuccessListener { documentsCarros ->
                        for (documentCarro in documentsCarros) {
                            if (documentCarro.id.toInt() == code) {
                                onSuccess(
                                    Carro(
                                        documentCarro.id.toInt(),
                                        document.code,
                                        documentCarro.getString("marca")!!,
                                        LocalDate.parse(documentCarro.data!!["fecha_elaboracion"].toString()),
                                        documentCarro.getDouble("precio")!!,
                                        documentCarro.getBoolean("color_subjetivo")!!,
                                        documentCarro.getDouble("meses_plazo_pagar")!!.toInt()
                                    )
                                )
                            }
                        }
                    }
            }
        }
    }

    override fun update(entity: Carro) {
        val carro = hashMapOf(
            "marca" to entity.marca,
            "fecha_elaboracion" to  entity.fecha_elaboracion.toString(),
            "precio" to entity.precio,
            "color_subjetivo" to entity.color_subjetivo,
            "meses_plazo_pagar" to entity.meses_plazo_pagar,
        )

        concesionarioCollectionReference
            .document(entity.deviceCode.toString())
            .collection("carros")
            .document(entity.code.toString()).set(carro)
    }

    override fun delete(code: Int, onSuccess: (Unit) -> Unit) {
        DAOFactory.factory.getConcesionarioDAO().getAllConcesionarios { documents ->
            for (document in documents) {
                val db = Firebase.firestore
                val carroCollectionReference = db.collection(
                    "concesionarios/${document.code}/carros"
                )

                carroCollectionReference
                    .get()
                    .addOnSuccessListener { documentsCarros ->
                        for (documentCarro in documentsCarros) {
                            if (documentCarro.id.toInt() == code) {
                                val carroReference = carroCollectionReference
                                    .document(code.toString())

                                carroReference.delete().addOnSuccessListener {
                                    onSuccess(Unit)
                                }
                            }
                        }
                    }
            }
        }
    }

    override fun getRandomCode(onSuccess: (Int) -> Unit) {
        var identificador = Date().time.toInt()
        if(identificador<0){
            identificador*=-1
        }
        onSuccess(identificador)
    }

}