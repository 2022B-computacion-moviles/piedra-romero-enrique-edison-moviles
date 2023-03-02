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

class ComponentCreation : AppCompatActivity() {
    /*private var componentParentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_creation)

        componentParentCode = intent.getIntExtra("componentParentCode", 0)

        val categoryPlainText = findViewById<EditText>(R.id.pl_component_creation_category)
        val descriptionPlainText = findViewById<EditText>(R.id.pl_component_creation_description)
        val discontinuedPlainText = findViewById<EditText>(R.id.pl_component_creation_discontinued)
        val parentCodePlainText = findViewById<EditText>(R.id.pl_component_creation_parent_code)
        val createButton = findViewById<Button>(R.id.btn_confirm_component_creation)
        val cancelButton = findViewById<Button>(R.id.btn_cancel_component_creation)

        parentCodePlainText.setText(componentParentCode.toString())

        // Opening creation dialog when next code is here
        createButton.setOnClickListener {
            DAOFactory.factory.getComponentDAO().getNextCode(
                onSuccess = { code ->
                    openCreationDialog(
                        ComponentEntity(
                            code,
                            categoryPlainText.text.toString(),
                            descriptionPlainText.text.toString(),
                            discontinuedPlainText.text.toString().toBoolean(),
                            componentParentCode!!
                        ),
                        componentParentCode!!
                    )
                }
            )
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this, ComponentList::class.java)
            intent.putExtra("selectedDeviceCode", componentParentCode)
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
    }*/
}