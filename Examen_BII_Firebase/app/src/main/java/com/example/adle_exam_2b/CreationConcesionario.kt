package com.example.adle_exam_2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.entity.Concesionario
import java.time.LocalDate

class CreationConcesionario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_concesionario)

        val nombrePlainText = findViewById<EditText>(R.id.create_concesionario_nombre)
        val fecha_inaguracionPlainText = findViewById<EditText>(R.id.create_concesionario_fecha)
        val porcentajePlainText = findViewById<EditText>(R.id.create_concesionario_porcentaje)
        val cantEmpleadosPlainText = findViewById<EditText>(R.id.create_concesionario_empleados)

        val createButton = findViewById<Button>(R.id.btn_confirm_concesionario_create)




        createButton.setOnClickListener {
            DAOFactory.factory.getConcesionarioDAO().getNextCode(
                onSuccess = { code ->
                    openCreationDialog(
                        Concesionario(
                            code,
                            nombrePlainText.text.toString(),
                            LocalDate.parse(fecha_inaguracionPlainText.text.toString()),
                            porcentajePlainText.text.toString().toDouble(),
                            cantEmpleadosPlainText.text.toString().toInt()
                        )
                    )
                }
            )
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun openCreationDialog(newConcesionario: Concesionario) {
        DAOFactory.factory.getConcesionarioDAO().create(newConcesionario)
        Toast.makeText(this, "Concesionario Creado", Toast.LENGTH_SHORT).show()
        openActivity(ListConcesionario::class.java)

    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}