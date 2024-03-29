package com.example.outlook.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.outlook.AppDataBase
import com.example.outlook.Correo
import com.example.outlook.R
import com.example.outlook.Seccion

class CorreoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    //val binding = ItemSuperheroBinding.bind(view)
    val emisor= view.findViewById<TextView>(R.id.correo_emisor)
    val receptor= view.findViewById<TextView>(R.id.correo_receptor)
    val mensaje= view.findViewById<TextView>(R.id.correo_mensaje)

    val menuIcon= view.findViewById<ImageView>(R.id.menu_button)

    val database = AppDataBase.getInstance(view.context)
    var secciones = emptyList<Seccion>()


    fun render(
        correoModel: Correo,
        onClickListener: (Correo) -> Unit,
        onClickListenerSeccion: (Seccion) -> Unit
    ) {
        emisor.text = correoModel.emisor
        receptor.text = correoModel.receptor
        mensaje.text = correoModel.mensaje
        itemView.setOnClickListener { onClickListener(correoModel)}



        val popupMenu = PopupMenu(itemView.context, menuIcon)
        popupMenu.menuInflater.inflate(R.menu.menu_correo, popupMenu.menu)

        //SUBMENÚ
        secciones = database.seccionDao.getAll().toMutableList()
        val subMenu = popupMenu.menu.addSubMenu("Mover a")
        secciones.forEach { seccion ->
            subMenu.add(seccion.nameseccion).setOnMenuItemClickListener {
                //Toast.makeText(itemView.context, "Usuario seleccionado: ${seccion.id} ${seccion.nameseccion} ${correoModel.emisor}", Toast.LENGTH_SHORT).show()
                correoModel.idseccion=seccion.id
                database.correoDao.update(correoModel)

                onClickListenerSeccion(seccion)
                true
            }
        }


        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_correo_ver -> {
                    //emisor.text = "eliminado"
                    onClickListener(correoModel)
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