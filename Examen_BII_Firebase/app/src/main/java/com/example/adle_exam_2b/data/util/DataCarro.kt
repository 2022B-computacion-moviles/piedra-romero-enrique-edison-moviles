package com.example.adle_exam_2b.data.util

import com.example.adle_exam_2b.data.entity.Carro
import java.time.LocalDate

class DataCarro {

    companion object {
        var componentEntityData = ArrayList<Carro>()

        init {
            componentEntityData.add(
                Carro(
                    1, 1,"Car1",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )

            componentEntityData.add(
                Carro(
                    2, 1,"Car2",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )

            componentEntityData.add(
                Carro(
                    3, 2,"Car2",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )


        }
    }

}