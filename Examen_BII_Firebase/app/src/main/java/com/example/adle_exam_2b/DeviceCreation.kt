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

class DeviceCreation : AppCompatActivity() {
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_creation)

        val namePlainText = findViewById<EditText>(R.id.pl_device_name)
        val categoryPlainText = findViewById<EditText>(R.id.pl_device_category)
        val datePlainText = findViewById<EditText>(R.id.pl_device_date)
        val pricePlainText = findViewById<EditText>(R.id.pl_device_price)
        val createButton = findViewById<Button>(R.id.btn_confirm_device_creation)
        val cancelButton = findViewById<Button>(R.id.btn_cancel_creation)

        // Opening creation dialog when next code is ready
        createButton.setOnClickListener {
            DAOFactory.factory.getDeviceDAO().getNextCode(
                onSuccess = { code ->
                    openCreationDialog(
                        DeviceEntity(
                            code,
                            namePlainText.text.toString(),
                            categoryPlainText.text.toString(),
                            LocalDate.parse(datePlainText.text.toString()),
                            pricePlainText.text.toString().toDouble()
                        )
                    )
                }
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
    private fun openCreationDialog(createdDevice: DeviceEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Create device")
        builder.setMessage("Are you sure you want to create the device?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getDeviceDAO().create(createdDevice)
            openActivity(DeviceList::class.java)
        }

        builder.setNegativeButton("No") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }*/
}