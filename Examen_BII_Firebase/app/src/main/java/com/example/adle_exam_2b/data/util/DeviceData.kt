package com.example.adle_exam_2b.data.util

import com.example.adle_exam_2b.data.entity.DeviceEntity
import java.time.LocalDate

class DeviceData {

    companion object {
        var deviceEntityData = ArrayList<DeviceEntity>()

        init {
            deviceEntityData.add(
                DeviceEntity(
                    1, "Concesionario1",  LocalDate.parse("2022-02-21"), 59.4,45982
                )
            )

            deviceEntityData.add(
                DeviceEntity(
                    2, "Concesionario2",  LocalDate.parse("2022-01-21"), 59.4,45982
                )
            )


        }
    }

}