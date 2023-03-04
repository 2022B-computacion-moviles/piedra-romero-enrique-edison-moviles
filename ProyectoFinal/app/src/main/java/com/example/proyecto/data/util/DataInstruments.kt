package com.example.proyecto.data.util

import com.example.proyecto.data.entity.Instrument

class DataInstruments {

    companion object {
        var instrumentsData = ArrayList<Instrument>()

        init {
            instrumentsData.add(Instrument(1,"Guitarra", "Cuerda","Es una guitarra",12, 450.5,"https://lavictoria.ec/wp-content/uploads/2023/01/GUITARRA-CLASICA-YAMAHA-C80-1-600x600.jpg"))
            instrumentsData.add(Instrument(2,"Guitarra2", "Cuerda","Es una guitarra2",12, 450.5,"https://livansud.vteximg.com.br/arquivos/ids/158399-1000-1000/guitarra-acustica-eko-azul-eckohogar-1.jpg?v=637461695140370000"))
        }
    }
}