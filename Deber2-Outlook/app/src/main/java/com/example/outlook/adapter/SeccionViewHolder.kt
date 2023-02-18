package com.example.outlook.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.outlook.R
import com.example.outlook.Seccion
import org.w3c.dom.Text

class SeccionViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    //val binding = ItemSuperheroBinding.bind(view)
    val nameseccion= view.findViewById<TextView>(R.id.seccion_name)

    fun render(
        seccionModel: Seccion
    ) {
        nameseccion.text = seccionModel.nameseccion

    }
}