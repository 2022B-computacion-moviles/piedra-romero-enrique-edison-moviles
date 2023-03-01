package com.example.adle_exam_2b.data.util

import com.example.adle_exam_2b.data.entity.DeviceEntity
import java.time.LocalDate

class DeviceData {

    companion object {
        var deviceEntityData = ArrayList<DeviceEntity>()

        init {
            deviceEntityData.add(
                DeviceEntity(
                    1, "TV NEO 4K", "Video", LocalDate.parse("2022-02-21"), 2385.53
                )
            )

            deviceEntityData.add(
                DeviceEntity(
                    2, "Wireless Speaker", "Audio", LocalDate.parse("2022-04-12"), 499.99
                )
            )

            deviceEntityData.add(
                DeviceEntity(
                    3, "TV Samsung UHD", "Video", LocalDate.parse("2019-10-21"), 274.49
                )
            )

            deviceEntityData.add(
                DeviceEntity(4, "Samsung Galaxy S4", "Cellphone", LocalDate.parse("2014-07-10"), 355.69)
            )
        }
    }

}