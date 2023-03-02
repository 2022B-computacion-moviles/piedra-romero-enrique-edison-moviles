package com.example.adle_exam_2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.entity.Carro

class ListCarro : AppCompatActivity() {
    private var selectedComponentCode: Int? = null
    private var componentParentCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_list)

        componentParentCode = intent.getIntExtra("selectedDeviceCode", 0)

        val componentRecyclerView = findViewById<RecyclerView>(R.id.rv_component)
        val deviceCodeTextView = findViewById<TextView>(R.id.tv_parent_component_code)
        val deviceNameTextView = findViewById<TextView>(R.id.tv_parent_component_name)
        val creationButton = findViewById<Button>(R.id.btn_create_component)

        // Setting component device code when it is ready
        DAOFactory.factory.getDeviceDAO().read(
            componentParentCode!!,
            onSuccess = { device ->
                deviceCodeTextView.text = device.code.toString()
                deviceNameTextView.text = device.nombre
            }
        )

        // Setting the Recycler View when the data is ready
        DAOFactory.factory.getComponentDAO().getAllCarrosByCodeCar(
            componentParentCode!!,
            onSuccess = { components ->
                initializeRecyclerView(components, componentRecyclerView)
                registerForContextMenu(componentRecyclerView)
            }
        )

        creationButton.setOnClickListener {
            val intent = Intent(this, CreationCarro::class.java)
            intent.putExtra("componentParentCode", componentParentCode)
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
                openActivity(ListConcesionario::class.java)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_component_edit -> {
                val intent = Intent(this, EditionCarro::class.java)
                intent.putExtra("selectedComponentCode", selectedComponentCode)
                startActivity(intent)
                return true
            }

            R.id.mi_component_delete -> {
                openDeleteDialog()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun setSelectedComponentCode(componentCode: Int) {
        selectedComponentCode = componentCode
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openDeleteDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete component")
        builder.setMessage("Are you sure you want to delete the component?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getComponentDAO().delete(
                selectedComponentCode!!,
                onSuccess = {
                    DAOFactory.factory.getComponentDAO().getAllCarrosByCodeCar(
                        componentParentCode!!,
                        onSuccess = { components ->
                            initializeRecyclerView(components, findViewById(R.id.rv_component))
                        }
                    )
                }
            )
        }

        builder.setNegativeButton("No") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(
        list: ArrayList<Carro>,
        recyclerView: RecyclerView
    ) {
        val adapter = RcVwAdapterCarro(this, list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}