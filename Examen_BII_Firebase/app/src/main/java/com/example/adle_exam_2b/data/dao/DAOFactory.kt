package com.example.adle_exam_2b.data.dao

import com.example.adle_exam_2b.data.firebase.FirebaseDAOFactory

abstract class DAOFactory {

    companion object {
        var factory: DAOFactory = FirebaseDAOFactory()
    }

    abstract fun getConcesionarioDAO(): ConcesionarioDAO
    abstract fun getCarroDAO(): CarroDAO

}