package com.example.adle_exam_2b.data.firebase

import com.example.adle_exam_2b.data.dao.CarroDAO
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.dao.ConcesionarioDAO

class FirebaseDAOFactory: DAOFactory() {

    override fun getDeviceDAO(): ConcesionarioDAO {
        return FirebaseConcesionarioDAO()
    }

    override fun getComponentDAO(): CarroDAO {
        return FirebaseCarroDAO()
    }

}