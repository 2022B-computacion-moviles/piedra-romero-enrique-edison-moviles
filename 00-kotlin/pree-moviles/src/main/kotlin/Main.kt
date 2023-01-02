
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")
    //Tipos de variables

    //INMUTABLES (val; no re asigna)
    val inmutable: String ="enrique";
    //inmutable="edison"

    //MUTABLES (var; si reasigna)
    var mutable: String ="enrique";
    mutable="edison"

    //val > var


    //Duck Typing
    val ejemploVariable="ejemplo"
    ejemploVariable.trim()
    val edadEjemplo: Int =12

    //Variables primitivas
    val nombreProf:String="Adrian Eguez"
    val sueldo: Double=1.2
    val estadoCivil:Char='S'
    val mayorEdad:Boolean=true

    //Clases
    val fechaNacimiento: Date = Date()  //no se usa new en clases

    //if(true){
    //}else{ }

    //switch no existe

    val estadoCivilWhen="S"

    when (estadoCivilWhen){
        ("S")->{
            println("Soltero")
        }
        "C"-> println("Casado")
        else-> println("Desconido")
    }

    val coqueto=if(estadoCivilWhen=="S") "Si" else "No"
    println(coqueto)

    val suma1=Suma(1,2)
    val suma2=Suma(1,null)
    val suma3=Suma(null,2)
    val suma4=Suma(null,null)
    suma1.sumar()
    suma2.sumar()
    suma3.sumar()
    suma4.sumar()
    println(Suma.historialSumas)

    //Arreglo estativo
    val arregloEstatico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3)
    println(arregloEstatico)

    //Arreglo dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //Operadores -> Sirven para los arreglos estaticos y dinamicos
    //FOR EACH -> Unit
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valorActual: Int -> //código
            println("Valor actual: ${valorActual}")
        }
    println(respuestaForEach)

    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)



    //MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00 //@ pra cambiar el arreglo
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 } //cuando solo hay 1 parametro
    /*
        .map { valorActual: Int ->
            return@map valorActual + 15
         }
     */
    println(respuestaMapDos)



    /*Filter -> FILTRAR EL ARREGLO
         1) Devolver una expresion (TRUE o FALSE)
         2) Nuevo arreglo filtrado
    */
    val respuestaFilter: List<Int> = arregloDinamico.filter { valorActual: Int ->
        val mayoresACinco: Boolean = valorActual > 5
        return@filter mayoresACinco
    }
    val respuesFilterDos = arregloDinamico.filter { it <= 5 } //froma reducida
    println(respuestaFilter)
    println(respuesFilterDos)



    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false





    // REDUCE -> Valor acumulado
    // valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    //[1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 18 + 5 = 15 -> Iteracion 5
    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN O
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) // -> Logica negocio
        }
    println(respuestaReduce)











}


fun imprimirNombre(nombre:String):Unit{  //No hay void, usa Unit
    println("Nombre :${nombre}")
}
                  //Requerido,   opcional por defecto,  opcional nulo
fun calcularSueldo(sueldo:Double, tasa:Double=12.00, bonoEspecial:Double?=null ):Double{
    if(bonoEspecial!=null){
        return sueldo*tasa*bonoEspecial
    }
                      return sueldo*tasa
}

abstract class NumerosJava{
    protected val numeroUno:Int
    protected val numeroDos:Int
    constructor(uno:Int,dos:Int){
        this.numeroUno=uno;
        this.numeroDos=dos;
        println("Iniciando")
    }
}


//Propio en Kotlin
abstract class Numeros(//Constructor primario
    //uno:Int, //Parametro
    //public var uno: Int, //Propiedad de la clase publica
    protected val numeroUno:Int,  //Propiedad
    protected val numeroDos:Int  //Propiedad
){
    init { //Bloque codigo constructor primario
        numeroUno
        numeroDos
        println("Iniciando")
    }
}

class Suma(uno: Int, dos:Int):Numeros(uno,dos /*Super constructor*/){
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor(
        //Segundo constructor
        uno: Int?,
        dos: Int
    ): this(
        if(uno==null) 0 else uno,
        dos
    ){}

    constructor(
        //Tercer constructor
        uno: Int,
        dos: Int?
    ): this(
        uno,
        if(dos==null) 0 else dos,

    ){}

    constructor(
        //Cuarto constructor
        uno: Int?,
        dos: Int?
    ): this(
        if(uno==null) 0 else uno,
        if(dos==null) 0 else dos,
    ){}

    fun sumar():Int{
        val total=numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }

    companion object{
        val pi=3.14 //disponible para todas las instancias
        val historialSumas= arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }


}





