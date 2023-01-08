package com.example.movcomp_eepr

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador= arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador.add(BEntrenador("Enrique", "enrique@a.com"))
            arregloBEntrenador.add(BEntrenador("Edison", "edison@a.com"))
            arregloBEntrenador.add(BEntrenador("Ricker", "ricker@a.com"))

        }
    }
}