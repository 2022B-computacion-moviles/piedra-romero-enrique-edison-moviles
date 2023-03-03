package com.example.adle_exam_2b

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.entity.Concesionario
import kotlinx.coroutines.*

class ListConcesionario : AppCompatActivity() {
    private var selectedConcesionarioCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_concesionario)

        val concesionarioRecyclerView = findViewById<RecyclerView>(R.id.rv_concesionario)
        val creationButton = findViewById<Button>(R.id.btn_create_concesionario)


        DAOFactory.factory.getConcesionarioDAO().getAllConcesionarios(
            onSuccess = { concesionarios ->
                initializeRecyclerView(concesionarios, concesionarioRecyclerView)
                registerForContextMenu(concesionarioRecyclerView)
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
            R.id.menu_concesionario_ver -> {
                val intent = Intent(this, ListCarro::class.java)
                intent.putExtra("selectedConcesionarioCode", selectedConcesionarioCode)
                startActivity(intent)
                return true
            }

            R.id.menu_concesionario_edit -> {
                val intent = Intent(this, EditionConcesionario::class.java)
                intent.putExtra("selectedConcesionarioCode", selectedConcesionarioCode)
                startActivity(intent)
                return true
            }

            R.id.menu_concesionario_delete -> {
                openDeleteDialog()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun setSelectedDeviceCode(concesionarioCode: Int) {
        selectedConcesionarioCode = concesionarioCode
    }

    private fun openDeleteDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar")
        builder.setMessage("Estas seguro de eliminar este Concesionario?")

        builder.setPositiveButton("Si") { _, _ ->
            DAOFactory.factory.getConcesionarioDAO().delete(
                selectedConcesionarioCode!!,
                onSuccess = {
                    DAOFactory.factory.getConcesionarioDAO().getAllConcesionarios(
                        onSuccess = { concesionarios ->
                            initializeRecyclerView(concesionarios, findViewById(R.id.rv_concesionario))
                        }
                    )
                }
            )
            Toast.makeText(this, "Concesionario Eliminado", Toast.LENGTH_SHORT).show()
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