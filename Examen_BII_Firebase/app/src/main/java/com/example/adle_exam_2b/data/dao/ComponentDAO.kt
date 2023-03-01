package com.example.adle_exam_2b.data.dao

import com.example.adle_exam_2b.data.entity.ComponentEntity

interface ComponentDAO: GenericDAO<ComponentEntity, Int> {

    fun getAllComponentsByDeviceCode(
        deviceCode: Int,
        onSuccess: (ArrayList<ComponentEntity>) -> Unit
    )

}