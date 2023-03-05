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
import android.os.Handler
import android.widget.ImageView
import com.example.proyecto.sesion.Sesion

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

        //Realizar Pago
        pagar()

        //Header & Auth
        val btnHeader = findViewById<Button>(R.id.header_button)
        val logoHeader = findViewById<ImageView>(R.id.header_logo)
        Sesion.sesionCurrent(this,btnHeader)
        Sesion.menuSesion(this,btnHeader)
        Sesion.headerLogo(this, logoHeader)

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_carrito_eliminar -> {
                FirebaseGlobal.firebaseCarrito.delete(selectedCarrito.codeCarrito){
                    Handler().postDelayed({
                        Toast.makeText(this, "Eliminado de Carrito", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ListCarritoUser::class.java))
                    }, 1000)
                }

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun pagar(){
        val btnPagar = findViewById<Button>(R.id.btn_carrito_pagar)
        btnPagar.setOnClickListener {
            FirebaseGlobal.firebaseCarrito.getAllCarritos {
                carritos ->
                for(carrito in carritos){
                    FirebaseGlobal.firebaseCompra.create(
                        carrito
                    )
                }
            }
            Handler().postDelayed({
                Toast.makeText(this, "Gracias por su compra", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ListCarritoUser::class.java))
            }, 1000)


        }

    }

    fun setSelectedCarrito(instrumentCarritov1: Carrito) {
        selectedCarrito = instrumentCarritov1
    }

}