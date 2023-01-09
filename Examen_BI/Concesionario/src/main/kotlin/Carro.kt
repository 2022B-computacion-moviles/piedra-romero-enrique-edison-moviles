import java.util.Date

class Carro(
    var marca: String,
    var fecha_elaboracion: Date,
    var precio: Double,
    var color_subjetivo: Boolean,
    var meses_plazo_pagar: Int
) {
    fun mostrarAtributos() {
        print("\tMarca: $marca\n")
        print("\tFecha Elaboración: $fecha_elaboracion\n")
        print("\tPrecio: $precio\n")
        print("\tPuede cambiar el color: $color_subjetivo\n")
        print("\tMeses plazo para pagar: $meses_plazo_pagar\n")
        print("\n")
    }

}