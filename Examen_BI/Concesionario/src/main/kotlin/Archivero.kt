import com.google.gson.Gson
import java.io.FileWriter
import java.io.File
import java.io.FileReader
import com.google.gson.reflect.TypeToken

class Archivero (
    var gson: Gson = Gson(),
    //Dirección del Archivo
    val file: File = File ("src/main/kotlin/Archivos/concesionario.json")

    ){
    fun crear_archivo(){
        FileWriter(file).use {
            it.write("")
        }
    }


    //Sobreescribe el json
    fun modificar_info(concesionario: ArrayList<Concesionario>) {
        // convertir el objeto Concesionario a formato JSON
        val json = gson.toJson(concesionario)

        // escribir el JSON en un archivo
        FileWriter(file).use {
            it.write(json, 0, json.length)
        }
        println("Modificado")
    }



    fun agregar_info(newconcesionario: Concesionario){
        if (file.exists()) {

            val read_json = FileReader(file).use {
                it.readText()
            }

            if (read_json == "") {
                println("vacio")
                var concesionarios= arrayListOf<Concesionario>(newconcesionario)
                val jsonActualizado = gson.toJson(concesionarios)

                // sobrescribe el json
                FileWriter(file).use {
                    it.write(jsonActualizado, 0, jsonActualizado.length)
                }


            } else {
                val type = object : TypeToken<ArrayList<Concesionario>>() {}.type
                val concesionarios: ArrayList<Concesionario> = gson.fromJson(read_json, type)
                //val concesionarios: ArrayList<Concesionario> = gson.fromJson(read_json, ArrayList<Concesionario>()::class.java)

                /*for (conc in concesionarios){
                    print(conc.mostrarCarros())
                }*/

                concesionarios.add(newconcesionario)
                val jsonActualizado = gson.toJson(concesionarios)

                // sobrescribe el json
                FileWriter(file).use {
                    it.write(jsonActualizado, 0, jsonActualizado.length)
                }
            }
        }
        else{
            println("No existe")
            crear_archivo()
            var concesionarios= arrayListOf<Concesionario>(newconcesionario)
            val jsonActualizado = gson.toJson(concesionarios)

            // sobrescribe el json
            FileWriter(file).use {
                it.write(jsonActualizado, 0, jsonActualizado.length)
            }
        }
        println("Insertado")
    }

    fun leer_info(): ArrayList<Concesionario>{
        var concesionarios= arrayListOf<Concesionario>()
        if (file.exists()) {
            val read_json = FileReader(file).use {
                it.readText()
            }
            if(read_json==""){
                concesionarios= arrayListOf<Concesionario>()
            }else{
                val type = object : TypeToken<ArrayList<Concesionario>>() {}.type
                var concesionarios_json: ArrayList<Concesionario> = gson.fromJson(read_json, type)
                concesionarios=concesionarios_json
            }
        }
        else {
            concesionarios= arrayListOf<Concesionario>()
        }
        return concesionarios
    }

    fun editar_info(){

    }

    fun eliminar_info(){

    }
}