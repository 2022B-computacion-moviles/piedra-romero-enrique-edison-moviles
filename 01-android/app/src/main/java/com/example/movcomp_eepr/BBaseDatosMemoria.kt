package com.example.movcomp_eepr

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador(1,"Enrique", "enrique@a.com"))
            arregloBEntrenador.add(BEntrenador(2,"Edison", "edison@a.com"))
            arregloBEntrenador.add(BEntrenador(3,"Ricker", "ricker@a.com"))
        }
    }
}

