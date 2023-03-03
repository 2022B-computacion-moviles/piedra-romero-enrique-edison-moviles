package com.example.adle_exam_2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.entity.Carro
import java.time.LocalDate

class CreationCarro : AppCompatActivity() {
    private var componentParentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_carro)

        componentParentCode = intent.getIntExtra("componentParentCode", 0)

        val codeConcesionarioPlainText = findViewById<TextView>(R.id.create_car_code_concesionario)

        val marcaPlainText = findViewById<EditText>(R.id.create_car_marca)
        val fecha_elaboracionPlainText = findViewById<EditText>(R.id.create_car_fecha)
        val precioPlainText = findViewById<EditText>(R.id.create_car_precio)
        val colorPlainText = findViewById<EditText>(R.id.create_car_color)
        val mesesPlainText = findViewById<EditText>(R.id.create_car_meses)

        val createButton = findViewById<Button>(R.id.btn_confirm_car_create)

        codeConcesionarioPlainText.setText(componentParentCode.toString())

        // Opening creation dialog when next code is here
        createButton.setOnClickListener {
            DAOFactory.factory.getCarroDAO().getNextCode(
                onSuccess = { code ->
                    openCreationDialog(
                        Carro(
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


    @SuppressLint("NotifyDataSetChanged")
    private fun openCreationDialog(newCarro: Carro, componentParentCode: Int) {
        DAOFactory.factory.getCarroDAO().create(newCarro)
        Toast.makeText(this, "Carro Creado", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListCarro::class.java)
        intent.putExtra("selectedDeviceCode", componentParentCode)
        startActivity(intent)

    }

}