package com.example.outlook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.outlook.Correo
import com.example.outlook.R
import com.example.outlook.Seccion

class CorreoAdapter(private val correoList: List<Correo>, private val onClickListener: (Correo) -> Unit, private val onClickListenerSeccion: (Seccion) -> Unit): RecyclerView.Adapter<CorreoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CorreoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CorreoViewHolder(layoutInflater.inflate(R.layout.item_correo, parent, false))
    }

    override fun onBindViewHolder(holder: CorreoViewHolder, position: Int) {
        val item = correoList[position]
        holder.render(item, onClickListener,onClickListenerSeccion)
    }

    override fun getItemCount(): Int = correoList.size
}