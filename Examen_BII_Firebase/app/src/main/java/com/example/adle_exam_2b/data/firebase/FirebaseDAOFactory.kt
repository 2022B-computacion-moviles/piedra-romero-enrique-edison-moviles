package com.example.adle_exam_2b.data.firebase

import com.example.adle_exam_2b.data.dao.ComponentDAO
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.dao.DeviceDAO

class FirebaseDAOFactory: DAOFactory() {

    override fun getDeviceDAO(): DeviceDAO {
        return FirebaseDeviceDAO()
    }

    override fun getComponentDAO(): ComponentDAO {
        return FirebaseComponentDAO()
    }

}