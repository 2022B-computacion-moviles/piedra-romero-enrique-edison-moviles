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

class EditionConcesionario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edition_concesionario)

        val codePlainText = findViewById<EditText>(R.id.edit_concesionario_code)
        val nombrePlainText = findViewById<EditText>(R.id.edit_concesionario_nombre)
        val fecha_inaguracionPlainText = findViewById<EditText>(R.id.edit_concesionario_fecha)
        val porcentajePlainText= findViewById<EditText>(R.id.edit_concesionario_porcentaje)
        val cantEmpleadosPlainText = findViewById<EditText>(R.id.edit_concesionario_empleados)
        val editButton = findViewById<Button>(R.id.btn_confirm_concesionario_edition)


        val selectedDeviceCode = intent.getIntExtra("selectedDeviceCode", 0)

        // Setting device data when it is ready
        DAOFactory.factory.getConcesionarioDAO().read(
            selectedDeviceCode,
            onSuccess = { device ->
                codePlainText.setText(device.code.toString())
                nombrePlainText.setText(device.nombre)
                fecha_inaguracionPlainText.setText(device.fecha_inaguracion.toString())
                porcentajePlainText.setText(device.porcentaje_personas_satisfechas.toString())
                cantEmpleadosPlainText.setText(device.cantidad_empleados.toString())
            }
        )

        editButton.setOnClickListener {
            openEditionDialog(
                Concesionario(
                    selectedDeviceCode,
                    nombrePlainText.text.toString(),
                    LocalDate.parse(fecha_inaguracionPlainText.text.toString()),
                    porcentajePlainText.text.toString().toDouble(),
                    cantEmpleadosPlainText.text.toString().toInt()
                )
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
    private fun openEditionDialog(editedDevice: Concesionario) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit device")
        builder.setMessage("Are you sure you want to edit the device?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getConcesionarioDAO().update(editedDevice)
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