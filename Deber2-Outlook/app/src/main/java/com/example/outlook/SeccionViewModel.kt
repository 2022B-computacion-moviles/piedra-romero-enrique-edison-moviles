package com.example.outlook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class SeccionViewModel(application: Application) : AndroidViewModel(application) {
    /*private val seccionDao = AppDataBase.getInstance(application).seccionDao
    val seccionesLiveData: LiveData<List<Seccion>> = seccionDao.getAll()


    fun seccionsDefault(){
        seccionDao.insert(Seccion(nameseccion = "Bandeja de entrada"))
        seccionDao.insert(Seccion(nameseccion = "Elementos enviados"))
        seccionDao.insert(Seccion(nameseccion = "Elementos eliminados"))
        seccionDao.insert(Seccion(nameseccion = "Correo no deseado"))
    }*/
}