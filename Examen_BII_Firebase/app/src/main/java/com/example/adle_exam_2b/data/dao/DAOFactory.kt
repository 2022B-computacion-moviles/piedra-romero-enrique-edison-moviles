package com.example.adle_exam_2b.data.dao

import com.example.adle_exam_2b.data.firebase.FirebaseDAOFactory

abstract class DAOFactory {

    companion object {
        var factory: DAOFactory = FirebaseDAOFactory()
    }

    abstract fun getDeviceDAO(): DeviceDAO
    abstract fun getComponentDAO(): ComponentDAO

}