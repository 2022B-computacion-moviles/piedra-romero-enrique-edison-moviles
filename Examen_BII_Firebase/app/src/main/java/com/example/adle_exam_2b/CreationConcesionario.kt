package com.example.adle_exam_2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
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



        // Opening creation dialog when next code is ready
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.back_button -> {
                openActivity(ListConcesionario::class.java)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openCreationDialog(createdDevice: Concesionario) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Create device")
        builder.setMessage("Are you sure you want to create the device?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getConcesionarioDAO().create(createdDevice)
            openActivity(ListConcesionario::class.java)
        }

        builder.setNegativeButton("No") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}