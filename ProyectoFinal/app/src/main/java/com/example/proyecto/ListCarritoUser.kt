package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.adapter.RcVwAdapterCarritos
import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.firebase.FirebaseGlobal

class ListCarritoUser : AppCompatActivity() {
    private lateinit var selectedCarrito: Carrito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_carrito_user)

        //RecyclerView
        val rvCarritos=findViewById<RecyclerView>(R.id.rv_carritos)
        FirebaseGlobal.firebaseCarrito.getAllCarritos { carritos ->
            initializeRecyclerView(carritos, rvCarritos)
            registerForContextMenu(rvCarritos)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(
        list: ArrayList<Carrito>,
        recyclerView: RecyclerView
    ) {
        val manager= LinearLayoutManager(this)
        //val manager= GridLayoutManager(this,2)
        val decoration= DividerItemDecoration(this, manager.orientation)

        val adapter = RcVwAdapterCarritos(this, list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(decoration)

        adapter.notifyDataSetChanged()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_carrito_eliminar -> {
                Toast.makeText(this, "Eliminado de Carrito", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun setSelectedCarrito(instrumentCarritov1: Carrito) {
        selectedCarrito = instrumentCarritov1
    }


}