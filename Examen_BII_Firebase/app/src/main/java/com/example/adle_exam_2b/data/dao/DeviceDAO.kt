package com.example.adle_exam_2b.data.dao

import com.example.adle_exam_2b.data.entity.DeviceEntity

interface DeviceDAO: GenericDAO<DeviceEntity, Int> {

    fun getAllDevices(onSuccess: (ArrayList<DeviceEntity>) -> Unit)

}