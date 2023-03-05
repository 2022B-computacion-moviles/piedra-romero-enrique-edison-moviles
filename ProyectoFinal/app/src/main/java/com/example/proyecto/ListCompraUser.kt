package com.example.proyecto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.adapter.RcVwAdapterCarritos
import com.example.proyecto.adapter.RcVwAdapterCompras
import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.entity.Compra
import com.example.proyecto.data.firebase.FirebaseGlobal
import com.example.proyecto.sesion.Sesion
import com.google.firebase.auth.FirebaseAuth

class ListCompraUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_compra_user)

        //RecyclerView
        val rvCompras=findViewById<RecyclerView>(R.id.rv_compras)
        FirebaseGlobal.firebaseCompra.getAllCompras { compras ->
            initializeRecyclerView(compras, rvCompras)
            registerForContextMenu(rvCompras)
        }

        //Header & Auth
        val btnHeader = findViewById<Button>(R.id.header_button)
        val logoHeader = findViewById<ImageView>(R.id.header_logo)
        Sesion.sesionCurrent(this,btnHeader)
        Sesion.menuSesion(this,btnHeader)
        Sesion.headerLogo(this, logoHeader)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(
        list: ArrayList<Compra>,
        recyclerView: RecyclerView
    ) {
        val manager= LinearLayoutManager(this)
        //val manager= GridLayoutManager(this,2)
        val decoration= DividerItemDecoration(this, manager.orientation)

        val adapter = RcVwAdapterCompras(this, list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(decoration)

        adapter.notifyDataSetChanged()
    }

}