package com.example.firebase.data.util

import com.example.firebase.data.entity.Carro
import java.time.LocalDate

class DataCarro {

    companion object {
        var carroData = ArrayList<Carro>()

        init {
            carroData.add(
                Carro(
                    1, 1,"Car1",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )

            carroData.add(
                Carro(
                    2, 1,"Car2",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )

            carroData.add(
                Carro(
                    3, 2,"Car2",  LocalDate.parse("2022-02-21"),10993.2, false, 4
                )
            )


        }
    }

}