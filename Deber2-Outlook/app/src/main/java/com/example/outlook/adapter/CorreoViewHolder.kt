package com.example.outlook.adapter

import android.view.View
import android.widget.TextView
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


    fun render(
        correoModel: Correo,
        onClickListener: (Correo) -> Unit
    ) {
        emisor.text = correoModel.emisor
        receptor.text = correoModel.receptor
        mensaje.text = correoModel.mensaje
        itemView.setOnClickListener { onClickListener(correoModel)}

    }
}