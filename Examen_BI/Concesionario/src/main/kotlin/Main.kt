import java.text.SimpleDateFormat
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
val reader = BufferedReader(InputStreamReader(System.`in`))
val miArchivero= Archivero()

fun main(args: Array<String>) {

    //var concesionarios= arrayListOf<Concesionario>()


    /*
    var miConcesionario = Concesionario(
        "Mi_Concesionario2",
        formatoFecha.parse("08/01/2023"),
        70.3,
        true,
        407,
        arrayOf(
            Carro("Marca 1", formatoFecha.parse("01/01/2001"), 20000.3, true, 7),
            Carro("Marca 2", formatoFecha.parse("01/03/2000"), 12300.39, false, 8)
        )
    )*/

    //miArchivero.agregar_info(miConcesionario)

    var select_activity: Int


    var option_general: Boolean=true

    while (option_general) {
        println("\t\tActividades a realizar")
        println("1. Insertar")
        println("2. Leer")
        println("3. Modificar")
        println("4. Eliminar")
        print("Ingrese su opción: ")
        select_activity = reader.readLine().toInt()

        when (select_activity) {
            1 -> {
                var option_1: Boolean = true
                while (option_1) {
                    create()
                    print("Salir de '1. Insertar'? (s/n): ")
                    option_1 = reader.readLine().toLowerCase() == "n"
                }

            }
            2 -> {
                var option_2: Boolean = true
                while (option_2) {
                    read()
                    print("Salir de '2. Leer'? (s/n): ")
                    option_2 = reader.readLine().toLowerCase() == "n"

                }


            }
            3 -> {
                var option_3: Boolean = true
                while (option_3) {
                    update()
                    print("Salir de '3. Modificar'? (s/n): ")
                    option_3 = reader.readLine().toLowerCase() == "n"

                }

            }
            4 -> {
                var option_4: Boolean = true
                while (option_4) {
                    delete()
                    print("Salir de '4. Eliminar'? (s/n): ")
                    option_4 = reader.readLine().toLowerCase() == "n"

                }

            }

        }
        print("Esta seguro de finalizar todo? (s/n): ")
        option_general = reader.readLine().toLowerCase() == "n"
    }

}


//FUNCIONES CRUD
fun create(){
    //Para concesionarios
    var nombre: String
    var fecha_inaguracion: Date
    var porcentaje_personas_satisfechas: Double
    var exporta_internacionalmente: Boolean
    var cantidad_empleados: Int
    //var carros: Array<Carro> = arrayOf()
    var carros = arrayListOf <Carro> ()



    var option: Boolean

    println("***Información del Concesionario***")

    print("Ingrese el nombre: ")
    nombre = reader.readLine()
    print("Ingrese la fecha de inaguración: ")
    fecha_inaguracion= formatoFecha.parse(reader.readLine())
    print("Ingrese el porcentaje de personas satisfechas: ")
    porcentaje_personas_satisfechas=reader.readLine().toDouble()
    print("Realiza exportaciones fuera del Pais? (s/n): ")
    exporta_internacionalmente = reader.readLine().toLowerCase() == "s"
    print("Cantidad de empleados: ")
    cantidad_empleados=reader.readLine().toInt()

    print("\tVa a ingresar Carros? (s/n): ")
    option= reader.readLine().toLowerCase() == "s"
    while(option){
        //Para Carros
        var marca: String
        var fecha_elaboracion: Date
        var precio: Double
        var color_subjetivo: Boolean
        var meses_plazo_pagar: Int

        print("\tIngrese la Marca: ")
        marca=reader.readLine()
        print("\tIngrese la fecha de elaboración: ")
        fecha_elaboracion=formatoFecha.parse(reader.readLine())
        print("\tIngrese el precio: ")
        precio=reader.readLine().toDouble()
        print("\tSe puede cambiar el color antes de la compra? (s/n): ")
        color_subjetivo= reader.readLine().toLowerCase() == "s"
        print("\tEn cuantos meses puede pagar?: ")
        meses_plazo_pagar=reader.readLine().toInt()

        carros.add(Carro(marca,fecha_elaboracion,precio,color_subjetivo,meses_plazo_pagar))
        println()

        print("\tVa a ingresar Carros? (s/n): ")
        option= reader.readLine().toLowerCase() == "s"
    }
    println()

    miArchivero.agregar_info(Concesionario(nombre,fecha_inaguracion,porcentaje_personas_satisfechas,exporta_internacionalmente,cantidad_empleados,carros))
}

fun read(){
    var option: Int
    var concesionarios_json=miArchivero.leer_info()

    println("1. Ver todos de todos los Concesionarios")
    println("2. Ver de un solo Concesionario")
    print("Opción: ")
    option= reader.readLine().toInt()

    if(option==1){
        for(concesionario in concesionarios_json){
            concesionario.mostrarConcesionario()
        }

    }
    else{
        var index:Int
        var option2_2: Boolean=true
        while(option2_2) {
            //println("Cantidad de concesionarios actuales (" + concesionarios_json.size + ")")
            var i:Int=1
            for(concesionario in concesionarios_json){
                println("$i"+". ${concesionario.nombre}")
                i++
            }
            print("Ingrese un número para leer el Concesionario (>0): ")
            index = reader.readLine().toInt()
            if (index > 0 && index <= concesionarios_json.size) {
                concesionarios_json[index - 1].mostrarConcesionario()
            }
            println()
            print("Continuar? (s/n): ")
            option2_2=reader.readLine().toLowerCase() == "s"
        }


    }

}

fun question_change(parametro: String): Boolean{
    print("Va a cambiar $parametro? (s/n):")
    return reader.readLine().toLowerCase() == "s"
}


fun update(){
    var option: Int
    var concesionarios_json=miArchivero.leer_info()

    println("1. Actualizar Concesionario")
    println("2. Actualizar Carro de un Concesionario")
    print("Opción: ")
    option= reader.readLine().toInt()

    if(option==1){
        var index:Int
        var option3_1: Boolean=true
        while(option3_1) {
            var i: Int = 1
            var index: Int
            for (concesionario in concesionarios_json) {
                println("$i" + ". ${concesionario.nombre}")
                i++
            }
            print("Ingrese un número para actualizar el Concesionario (>0): ")
            index = reader.readLine().toInt()
            if (index > 0 && index <= concesionarios_json.size) {
                var concesionario_aux=concesionarios_json[index - 1]
                //concesionarios_json.removeAt(index - 1)


                if (question_change("Nombre")){
                    reader.readLine()
                    concesionario_aux.nombre = reader.readLine()
                }
                if (question_change("Fecha de inaguración")){
                    reader.readLine()
                    concesionario_aux.fecha_inaguracion= formatoFecha.parse(reader.readLine())
                }
                if (question_change("Porcentaje de personas satisfechas")){
                    reader.readLine()
                    concesionario_aux.porcentaje_personas_satisfechas=reader.readLine().toDouble()
                }
                if (question_change("La opción de Exportaciones fuera del Pais")){
                    reader.readLine()
                    concesionario_aux.exporta_internacionalmente = reader.readLine().toLowerCase() == "s"
                }
                if (question_change("Cantidad de empleados")){
                    reader.readLine()
                    concesionario_aux.cantidad_empleados=reader.readLine().toInt()
                }
                //concesionarios_json.add(concesionario_aux)
                concesionarios_json[index - 1]=concesionario_aux

                miArchivero.modificar_info(concesionarios_json)
            }
            println()
            print("Continuar? (s/n): ")
            option3_1=reader.readLine().toLowerCase() == "s"
        }
    }
    else if(option==2){
        var i: Int = 1
        var index_con: Int
        for (concesionario in concesionarios_json) {
            println("$i" + ". ${concesionario.nombre}")
            i++
        }
        print("Ingrese un número para ingresar al Concesionario (>0): ")
        index_con = reader.readLine().toInt()

        if (index_con > 0 && index_con <= concesionarios_json.size) {

            var concesionario_aux=concesionarios_json[index_con-1]

            var option3_2:Boolean=true
            while(option3_2) {
                var j:Int=1

                for (carro in concesionario_aux.carros) {
                    println("$j" + ".")
                    println(carro.mostrarAtributos())
                    j++
                }

                var index_car: Int
                print("Ingrese un número para actualizar el Carro (>0): ")
                index_car = reader.readLine().toInt()
                if (index_car > 0 && index_car <= concesionario_aux.carros.size) {
                    var carro_aux=concesionario_aux.carros[index_car - 1]
                    //concesionario_aux.carros.removeAt(index_car - 1)


                    if (question_change("Marca")){
                        reader.readLine()
                        carro_aux.marca=reader.readLine()
                    }
                    if (question_change("Fecha de elaboración")){
                        reader.readLine()
                        carro_aux.fecha_elaboracion=formatoFecha.parse(reader.readLine())
                    }
                    if (question_change("Precio")){
                        reader.readLine()
                        carro_aux.precio=reader.readLine().toDouble()
                    }
                    if (question_change("Color antes de la compra")){
                        reader.readLine()
                        carro_aux.color_subjetivo= reader.readLine().toLowerCase() == "s"
                    }
                    if (question_change("Meses a pagar")){
                        reader.readLine()
                        carro_aux.meses_plazo_pagar=reader.readLine().toInt()
                    }

                    concesionario_aux.carros[index_car - 1]=carro_aux
                    //concesionario_aux.carros.add(carro_aux)

                    //concesionarios_json.removeAt(index_con-1)
                    //concesionarios_json.add(concesionario_aux)
                    concesionarios_json[index_con-1]=concesionario_aux

                    miArchivero.modificar_info(concesionarios_json)
                }

                print("Continuar? (s/n): ")
                option3_2 = reader.readLine().toLowerCase() == "s"
            }

        }
    }

}

fun delete(){
    var option: Int
    var concesionarios_json=miArchivero.leer_info()

    println("1. Eliminar Concesionario")
    println("2. Eliminar Carro de un Concesionario")
    println("3. Eliminar Todo")

    print("Opción: ")
    option= reader.readLine().toInt()

    if(option==1){
        var index:Int
        var option4_1: Boolean=true
        while(option4_1) {
            var i: Int = 1
            var index: Int
            for (concesionario in concesionarios_json) {
                println("$i" + ". ${concesionario.nombre}")
                i++
            }
            print("Ingrese un número para eliminar el Concesionario (>0): ")
            index = reader.readLine().toInt()
            if (index > 0 && index <= concesionarios_json.size) {
                concesionarios_json.removeAt(index - 1)
                miArchivero.modificar_info(concesionarios_json)
            }
            println()
            print("Continuar? (s/n): ")
            option4_1=reader.readLine().toLowerCase() == "s"
        }
    }
    else if(option==2){
        var i: Int = 1
        var index_con: Int
        for (concesionario in concesionarios_json) {
            println("$i" + ". ${concesionario.nombre}")
            i++
        }
        print("Ingrese un número para ingresar al Concesionario (>0): ")
        index_con = reader.readLine().toInt()

        if (index_con > 0 && index_con <= concesionarios_json.size) {

            var concesionario_aux=concesionarios_json[index_con-1]

            var option4_2:Boolean=true
            while(option4_2) {
                var j:Int=1
                var option4_2_1:Int

                for (carro in concesionario_aux.carros) {
                    println("$j" + ".")
                    println(carro.mostrarAtributos())
                    j++
                }
                println("1. Eliminar un carro")
                println("2. Eliminar todos los carros de este Concesionario")
                print("Opción: ")
                option4_2_1= reader.readLine().toInt()

                if(option4_2_1==1) {
                    var index_car: Int
                    print("Ingrese un número para eliminar el Carro (>0): ")
                    index_car = reader.readLine().toInt()
                    if (index_car > 0 && index_car <= concesionario_aux.carros.size) {
                        concesionario_aux.carros.removeAt(index_car - 1)
                        miArchivero.modificar_info(concesionarios_json)
                    }
                }
                else{
                    var option4_2_2: Boolean
                    print("Esta seguro de eliminar todos los carros del Concesionario? (s/n): ")
                    option4_2_2=reader.readLine().toLowerCase() == "s"
                    if(option4_2_2){
                        println("Eliminar Carros")
                        concesionario_aux.carros.clear()
                        miArchivero.modificar_info(concesionarios_json)
                    }


                }
                print("Continuar? (s/n): ")
                option4_2 = reader.readLine().toLowerCase() == "s"
            }

        }
    }
    else{
        var option4_3: Boolean
        print("Esta seguro de eliminar toda la información existente? (s/n): ")
        option4_3=reader.readLine().toLowerCase() == "s"
        if(option4_3){
            println("Eliminar todo")
            concesionarios_json.clear()
            miArchivero.modificar_info(concesionarios_json)
        }
    }

}




