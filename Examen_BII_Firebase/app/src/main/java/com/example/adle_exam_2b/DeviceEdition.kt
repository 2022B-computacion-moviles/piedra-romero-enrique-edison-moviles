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
import com.example.adle_exam_2b.data.entity.DeviceEntity
import java.time.LocalDate

class DeviceEdition : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_edition)

        val codePlainText = findViewById<EditText>(R.id.edit_device_code)
        val nombrePlainText = findViewById<EditText>(R.id.edit_device_nombre)
        val fecha_inaguracionPlainText = findViewById<EditText>(R.id.edit_device_fecha)
        val porcentajePlainText= findViewById<EditText>(R.id.edit_device_porcentaje)
        val cantEmpleadosPlainText = findViewById<EditText>(R.id.edit_device_empleados)
        val editButton = findViewById<Button>(R.id.btn_confirm_device_edition)
        val cancelButton = findViewById<Button>(R.id.btn_cancel_creation)

        val selectedDeviceCode = intent.getIntExtra("selectedDeviceCode", 0)

        // Setting device data when it is ready
        DAOFactory.factory.getDeviceDAO().read(
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
                DeviceEntity(
                    selectedDeviceCode,
                    nombrePlainText.text.toString(),
                    LocalDate.parse(fecha_inaguracionPlainText.text.toString()),
                    porcentajePlainText.text.toString().toDouble(),
                    cantEmpleadosPlainText.text.toString().toInt()
                )
            )
        }

        cancelButton.setOnClickListener {
            openActivity(DeviceList::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.back_button -> {
                openActivity(DeviceList::class.java)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openEditionDialog(editedDevice: DeviceEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit device")
        builder.setMessage("Are you sure you want to edit the device?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getDeviceDAO().update(editedDevice)
            openActivity(DeviceList::class.java)
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