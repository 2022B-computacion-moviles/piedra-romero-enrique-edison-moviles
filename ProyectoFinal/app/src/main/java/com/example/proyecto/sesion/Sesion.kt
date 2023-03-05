package com.example.proyecto.sesion

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import com.example.proyecto.*
import com.google.firebase.auth.FirebaseAuth

class Sesion {
    companion object {
        fun sesionCurrent(context: Context, btnHeader: Button) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val email = currentUser?.email.toString()
            if (email != null) {
                btnHeader.text = email.substring(0, 1).toUpperCase()
            }
        }
        fun headerLogo(context: Context, headerLogo: ImageView){
            headerLogo.setOnClickListener{
                context.startActivity(Intent(context, Home::class.java))
            }

        }

        fun menuSesion(context: Context, btnHeader: Button) {
            btnHeader.setOnClickListener { view ->
                val popupMenu = PopupMenu(context, view)
                popupMenu.inflate(R.menu.menu_sesion)

                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.menu_sesion_carrito -> {
                            context.startActivity(Intent(context, ListCarritoUser::class.java))
                            true
                        }
                        R.id.menu_sesion_salir -> {
                            FirebaseAuth.getInstance().signOut()
                            //onBackPressed()
                            context.startActivity(Intent(context, MainActivity::class.java))
                            true
                        }
                        R.id.menu_sesion_compras -> {
                            context.startActivity(Intent(context, ListCompraUser::class.java))
                            true
                        }
                        else -> false
                    }
                }

                popupMenu.show()
            }
        }

    }

}