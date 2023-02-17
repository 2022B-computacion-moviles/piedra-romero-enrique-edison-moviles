package com.example.outlook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterSeccion(private val secciones: List<Seccion>) :
    RecyclerView.Adapter<RecyclerViewAdapterSeccion.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameseccion: TextView
        init{
            nameseccion=itemView.findViewById(R.id.rv_seccion_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_seccion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val seccion = secciones[position]
        holder.nameseccion.text = seccion.nameseccion
    }

    override fun getItemCount(): Int = secciones.size

    fun getItem(position: Int): Seccion {
        return secciones[position]
    }


}