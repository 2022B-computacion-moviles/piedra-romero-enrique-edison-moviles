package com.example.firebase.data.util

import com.example.firebase.data.entity.Concesionario
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