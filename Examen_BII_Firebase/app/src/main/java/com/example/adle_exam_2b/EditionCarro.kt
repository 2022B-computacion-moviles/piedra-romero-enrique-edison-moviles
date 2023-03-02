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
import com.example.adle_exam_2b.data.entity.Carro
import java.time.LocalDate

class EditionCarro : AppCompatActivity() {
    private var selectedComponentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edition_carro)

        val codePlainText = findViewById<EditText>(R.id.edit_carro_code)
        val parentDevicePlainText = findViewById<EditText>(R.id.edit_carro_concesionario_code)

        val marcaPlainText = findViewById<EditText>(R.id.edit_carro_marca)
        val fecha_elaboracionPlainText = findViewById<EditText>(R.id.edit_carro_fecha)
        val precioPlainText = findViewById<EditText>(R.id.edit_carro_precio)
        val colorPlainText = findViewById<EditText>(R.id.edit_carro_color)
        val mesesPlainText = findViewById<EditText>(R.id.edit_carro_meses)

        val editButton = findViewById<Button>(R.id.btn_confirm_carro_edition)

        var selectedComponent: Carro? = null
        selectedComponentCode = intent.getIntExtra("selectedComponentCode", 0)

        // Setting component data when it is ready
        DAOFactory.factory.getCarroDAO().read(
            selectedComponentCode!!,
            onSuccess = { component ->
                selectedComponent = component

                codePlainText.setText(selectedComponent!!.code.toString())
                parentDevicePlainText.setText(selectedComponent!!.deviceCode.toString())

                marcaPlainText.setText(selectedComponent!!.marca)
                fecha_elaboracionPlainText.setText(selectedComponent!!.fecha_elaboracion.toString())
                precioPlainText.setText(selectedComponent!!.precio.toString())
                colorPlainText.setText(selectedComponent!!.color_subjetivo.toString())
                mesesPlainText.setText(selectedComponent!!.meses_plazo_pagar.toString())

                /* RECOGE ALGO?*/

            }
        )

        editButton.setOnClickListener {
            openEditionDialog(
                Carro(
                    selectedComponentCode!!,
                    selectedComponent!!.deviceCode,
                    marcaPlainText.text.toString(),
                    LocalDate.parse(fecha_elaboracionPlainText.text.toString()),
                    precioPlainText.text.toString().toDouble(),
                    colorPlainText.text.toString().toBoolean(),
                    mesesPlainText.text.toString().toInt()
                ),
                selectedComponent!!.deviceCode
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
                openActivityWithParameter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openEditionDialog(editedComponent: Carro, componentParentCode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit component")
        builder.setMessage("Are you sure you want to edit the component?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getCarroDAO().update(editedComponent)

            val intent = Intent(this, ListCarro::class.java)
            intent.putExtra("selectedDeviceCode", componentParentCode)
            startActivity(intent)
        }

        builder.setNegativeButton("No") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    private fun openActivityWithParameter() {
        DAOFactory.factory.getCarroDAO().read(
            selectedComponentCode!!,
            onSuccess = { component ->
                val intent = Intent(this, ListCarro::class.java)
                intent.putExtra("selectedDeviceCode", component.deviceCode)
                startActivity(intent)
            }
        )
    }
}