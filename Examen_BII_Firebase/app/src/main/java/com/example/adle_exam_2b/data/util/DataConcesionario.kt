package com.example.adle_exam_2b.data.util

import com.example.adle_exam_2b.data.entity.Concesionario
import java.time.LocalDate

class DataConcesionario {

    companion object {
        var concesionariosData = ArrayList<Concesionario>()

        init {
            concesionariosData.add(
                Concesionario(
                    1, "Concesionario1",  LocalDate.parse("2022-02-21"), 59.4,45982
                )
            )

            concesionariosData.add(
                Concesionario(
                    2, "Concesionario2",  LocalDate.parse("2022-01-21"), 59.4,45982
                )
            )


        }
    }

}