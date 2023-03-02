package com.example.adle_exam_2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.entity.Concesionario
import kotlinx.coroutines.*

class ListConcesionario : AppCompatActivity() {
    private var selectedDeviceCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        val deviceRecyclerView = findViewById<RecyclerView>(R.id.rv_device)
        val creationButton = findViewById<Button>(R.id.btn_create_device)


        DAOFactory.factory.getConcesionarioDAO().getAllConcesionarios(
            onSuccess = { devices ->
                initializeRecyclerView(devices, deviceRecyclerView)
                registerForContextMenu(deviceRecyclerView)
                /*android.app.AlertDialog.Builder(this)
                    .setTitle("Modificado")
                    .setMessage("Con Éxito ${devices.size}")
                    .setPositiveButton("Aceptar") { dialog, which ->
                        // acción cuando se presiona Aceptar
                    }
                    .setNegativeButton("Cancelar") { dialog, which ->
                        // acción cuando se presiona Cancelar
                    }
                    .create()
                    .show()*/

            }


        )

        creationButton.setOnClickListener {
            openActivity(CreationConcesionario::class.java)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_device_see_components -> {
                val intent = Intent(this, ListCarro::class.java)
                intent.putExtra("selectedDeviceCode", selectedDeviceCode)
                startActivity(intent)
                return true
            }

            R.id.mi_device_edit -> {
                val intent = Intent(this, EditionConcesionario::class.java)
                intent.putExtra("selectedDeviceCode", selectedDeviceCode)
                startActivity(intent)
                return true
            }

            R.id.mi_device_delete -> {
                openDeleteDialog()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun setSelectedDeviceCode(deviceCode: Int) {
        selectedDeviceCode = deviceCode
    }

    private fun openDeleteDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete device")
        builder.setMessage("Are you sure you want to delete the device?")

        builder.setPositiveButton("Yes") { _, _ ->
            DAOFactory.factory.getConcesionarioDAO().delete(
                selectedDeviceCode!!,
                onSuccess = {
                    DAOFactory.factory.getConcesionarioDAO().getAllConcesionarios(
                        onSuccess = { devices ->
                            initializeRecyclerView(devices, findViewById(R.id.rv_device))
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
        list: ArrayList<Concesionario>,
        recyclerView: RecyclerView
    ) {
        val adapter = RcVwAdapterConcesionario(this, list)

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