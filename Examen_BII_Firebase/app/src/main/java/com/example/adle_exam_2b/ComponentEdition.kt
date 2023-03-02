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

class ComponentEdition : AppCompatActivity() {
    /*private var selectedComponentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_edition)

        val codePlainText = findViewById<EditText>(R.id.pl_component_code)
        val categoryPlainText = findViewById<EditText>(R.id.pl_component_category)
        val descriptionPlainText = findViewById<EditText>(R.id.pl_component_description)
        val discontinuedPlainText = findViewById<EditText>(R.id.pl_component_discontinued)
        val parentCodePlainText = findViewById<EditText>(R.id.pl_component_parent_code)
        val editButton = findViewById<Button>(R.id.btn_confirm_component_edition)
        val cancelButton = findViewById<Button>(R.id.btn_cancel_edition)

        var selectedComponent: ComponentEntity? = null
        selectedComponentCode = intent.getIntExtra("selectedComponentCode", 0)

        // Setting component data when it is ready
        DAOFactory.factory.getComponentDAO().read(
            selectedComponentCode!!,
            onSuccess = { component ->
                selectedComponent = component

                codePlainText.setText(selectedComponent!!.code.toString())
                categoryPlainText.setText(selectedComponent!!.category)
                descriptionPlainText.setText(selectedComponent!!.description)
                discontinuedPlainText.setText(selectedComponent!!.discontinued.toString())
                parentCodePlainText.setText(selectedComponent!!.deviceCode.toString())
            }
        )

        editButton.setOnClickListener {
            openEditionDialog(
                ComponentEntity(
                    selectedComponentCode!!,
                    categoryPlainText.text.toString(),
                    descriptionPlainText.text.toString(),
                    discontinuedPlainText.text.toString().toBoolean(),
                    selectedComponent!!.deviceCode
                ),
                selectedComponent!!.deviceCode
            )
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this, ComponentList::class.java)
            intent.putExtra("selectedDeviceCode", selectedComponent!!.deviceCode)
            startActivity(intent)
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
    private fun openEditionDialog(editedComponent: ComponentEntity, componentParentCode: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit component")
        builder.setMessage("Are you sure you want to edit the component?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getComponentDAO().update(editedComponent)

            val intent = Intent(this, ComponentList::class.java)
            intent.putExtra("selectedDeviceCode", componentParentCode)
            startActivity(intent)
        }

        builder.setNegativeButton("No") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    private fun openActivityWithParameter() {
        DAOFactory.factory.getComponentDAO().read(
            selectedComponentCode!!,
            onSuccess = { component ->
                val intent = Intent(this, ComponentList::class.java)
                intent.putExtra("selectedDeviceCode", component.deviceCode)
                startActivity(intent)
            }
        )
    }*/
}