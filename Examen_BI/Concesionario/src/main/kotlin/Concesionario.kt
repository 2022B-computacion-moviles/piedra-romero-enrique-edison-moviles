import java.util.Date

class Concesionario(
    var nombre: String,
    var fecha_inaguracion: Date,
    var porcentaje_personas_satisfechas: Double,
    var exporta_internacionalmente: Boolean,
    var cantidad_empleados: Int,
    var carros: ArrayList<Carro>

) {
    fun mostrarConcesionario() {
        println("Nombre: $nombre")
        println("Fecha de Inaguración: $fecha_inaguracion")
        println("Porcentaje de personas satisfechas: $porcentaje_personas_satisfechas")
        println("Exporta fuera del Pais: $exporta_internacionalmente")
        println("Cantidad de Empleados: $cantidad_empleados")
        println("Carros del Concesionario: ")
        for (carro in carros) {
            println(carro.mostrarAtributos())
        }
        println()
    }



}