package com.example.outlook.adapter

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

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_correo_mover -> {
                    emisor.text = "movido"
                    return@setOnMenuItemClickListener true
                }
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