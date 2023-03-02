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
import com.example.adle_exam_2b.data.entity.ComponentEntity
import java.time.LocalDate

class ComponentCreation : AppCompatActivity() {
    private var componentParentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_creation)

        componentParentCode = intent.getIntExtra("componentParentCode", 0)

        val codeConcesionarioPlainText = findViewById<EditText>(R.id.creation_car_code_concesionario)

        val marcaPlainText = findViewById<EditText>(R.id.creation_car_marca)
        val fecha_elaboracionPlainText = findViewById<EditText>(R.id.creation_car_fecha)
        val precioPlainText = findViewById<EditText>(R.id.creation_car_precio)
        val colorPlainText = findViewById<EditText>(R.id.creation_car_color)
        val mesesPlainText = findViewById<EditText>(R.id.creation_car_meses)

        val createButton = findViewById<Button>(R.id.btn_confirm_car_creation)

        codeConcesionarioPlainText.setText(componentParentCode.toString())

        // Opening creation dialog when next code is here
        createButton.setOnClickListener {
            DAOFactory.factory.getComponentDAO().getNextCode(
                onSuccess = { code ->
                    openCreationDialog(
                        ComponentEntity(
                            code,
                            componentParentCode!!,
                            marcaPlainText.text.toString(),
                            LocalDate.parse(fecha_elaboracionPlainText.text.toString()),
                            precioPlainText.text.toString().toDouble(),
                            colorPlainText.text.toString().toBoolean(),
                            mesesPlainText.text.toString().toInt()
                        ),
                        componentParentCode!!
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
                openActivityWithParameter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openCreationDialog(createdComponent: ComponentEntity, componentParentCode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Create component")
        builder.setMessage("Are you sure you want to create the component?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getComponentDAO().create(createdComponent)

            val intent = Intent(this, ComponentList::class.java)
            intent.putExtra("selectedDeviceCode", componentParentCode)
            startActivity(intent)
        }

        builder.setNegativeButton("No") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    private fun openActivityWithParameter() {
        val intent = Intent(this, ComponentList::class.java)
        intent.putExtra("selectedDeviceCode", componentParentCode!!)
        startActivity(intent)
    }
}