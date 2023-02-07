package com.example.concesionario

import java.text.SimpleDateFormat

class BBaseDatosMemoria {
    companion object{
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        val arregloBConcesionario= arrayListOf<BConcesionario>()
        init {
            arregloBConcesionario.add(BConcesionario("Conc1",formatoFecha.parse("02/07/2001"), 104))
            arregloBConcesionario.add(BConcesionario("Conc2",formatoFecha.parse("02/07/2001"), 105))
            arregloBConcesionario.add(BConcesionario("Conc3",formatoFecha.parse("02/07/2001"), 106))
        }


    }
}