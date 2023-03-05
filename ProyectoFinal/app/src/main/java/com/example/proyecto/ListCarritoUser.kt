package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.adapter.RcVwAdapterCarritos
import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.firebase.FirebaseGlobal
import com.google.firebase.auth.FirebaseAuth

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


        //Header & Auth
        //SESIÓN
        sesionCurrent()
        //MENÚ SESIÓN
        menuSesion()

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

    private fun sesionCurrent(){
        //val emailText = findViewById<TextView>(R.id.home_email)
        val btnHeader = findViewById<Button>(R.id.header_button)
        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email.toString()
        if (email != null) {
            btnHeader.text = email.substring(0, 1).toUpperCase()
        }

    }

    private fun menuSesion(){
        val btnHeader = findViewById<Button>(R.id.header_button)
        btnHeader.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.inflate(R.menu.menu_sesion)

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_sesion_carrito -> {
                        startActivity(Intent(this, ListCarritoUser::class.java))
                        true
                    }
                    R.id.menu_sesion_salir -> {
                        FirebaseAuth.getInstance().signOut()
                        //onBackPressed()
                        startActivity(Intent(this, MainActivity::class.java))
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }

    }


}