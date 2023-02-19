package com.example.outlook.adapter

import android.view.SubMenu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.outlook.Correo
import com.example.outlook.R
import com.example.outlook.Seccion
import org.w3c.dom.Text

class CorreoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    //val binding = ItemSuperheroBinding.bind(view)
    val emisor= view.findViewById<TextView>(R.id.correo_emisor)
    val receptor= view.findViewById<TextView>(R.id.correo_receptor)
    val mensaje= view.findViewById<TextView>(R.id.correo_mensaje)

    val menuIcon= view.findViewById<ImageView>(R.id.menu_button)


    fun render(
        correoModel: Correo,
        onClickListener: (Correo) -> Unit
    ) {
        emisor.text = correoModel.emisor
        receptor.text = correoModel.receptor
        mensaje.text = correoModel.mensaje
        itemView.setOnClickListener { onClickListener(correoModel)}



        val popupMenu = PopupMenu(itemView.context, menuIcon)
        popupMenu.menuInflater.inflate(R.menu.menu_correo, popupMenu.menu)

        //SUBMENÚ
        val userList = listOf("Usuario 1", "Usuario 2", "Usuario 3")
        val subMenu = popupMenu.menu.addSubMenu("Mover a")
        userList.forEach { user ->
            subMenu.add(user).setOnMenuItemClickListener {
                Toast.makeText(itemView.context, "Usuario seleccionado: $user", Toast.LENGTH_SHORT).show()
                true
            }
        }


        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_correo_eliminar -> {
                    emisor.text = "eliminado"
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }

        menuIcon.setOnClickListener {
            popupMenu.show()
        }



    }


}