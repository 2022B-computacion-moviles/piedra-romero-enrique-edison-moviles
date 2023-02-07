package com.example.concesionario

import java.text.SimpleDateFormat

class BBaseDatosMemoria {
    companion object{
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        val arregloBConcesionario= arrayListOf<BConcesionario>()
        val arregloBCarro= arrayListOf<BCarro>()


        init {
            arregloBCarro.add(BCarro("carro1", formatoFecha.parse("02/02/2002"), 56.4, true,5))

            arregloBConcesionario.add(BConcesionario("Conc1",formatoFecha.parse("01/07/2001"), 34.5,  104,arregloBCarro))
            arregloBConcesionario.add(BConcesionario("Conc2",formatoFecha.parse("02/07/2001"), 34.5,  105,arregloBCarro))
            arregloBConcesionario.add(BConcesionario("Conc3",formatoFecha.parse("03/07/2001"), 34.5,  106,arregloBCarro))
        }


    }
}