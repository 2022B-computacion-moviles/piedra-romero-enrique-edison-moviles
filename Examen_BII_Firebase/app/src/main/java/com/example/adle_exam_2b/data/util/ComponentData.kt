package com.example.adle_exam_2b.data.util

import com.example.adle_exam_2b.data.entity.ComponentEntity
import java.time.LocalDate

class ComponentData {

    companion object {
        var componentEntityData = ArrayList<ComponentEntity>()

        init {
            componentEntityData.add(
                ComponentEntity(
                    1, 1,"Car1",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    2, 1,"Car2",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )

            componentEntityData.add(
                ComponentEntity(
                    3, 2,"Car2",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )


        }
    }

}