package com.example.concesionario

import android.widget.ArrayAdapter
import java.text.SimpleDateFormat
import java.util.*

class BBaseDatosMemoria {
    companion object{
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        val formatoEntrada = SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US)
        val formatoSalida = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        val arregloBConcesionario= arrayListOf<BConcesionario>()
        val arregloBCarro= arrayListOf<BCarro>()

        lateinit var adaptador: ArrayAdapter<BConcesionario>
        lateinit var adaptadorCarros: ArrayAdapter<BCarro>

        //val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloBConcesionario)

        init {
            arregloBCarro.add(BCarro("carro1", formatoFecha.parse("02/02/2002"), 56.4, true,5))
            arregloBCarro.add(BCarro("carro2", formatoFecha.parse("01/02/2002"), 56.4, false,6))


            arregloBConcesionario.add(BConcesionario("Conc1",formatoFecha.parse("01/07/2001"), 34.5,  104,arregloBCarro))
            arregloBConcesionario.add(BConcesionario("Conc2",formatoFecha.parse("02/07/2001"), 34.5,  105,arregloBCarro))
            arregloBConcesionario.add(BConcesionario("Conc3",formatoFecha.parse("03/07/2001"), 34.5,  106,arregloBCarro))
            //adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloBConcesionario)

        }


    }
}