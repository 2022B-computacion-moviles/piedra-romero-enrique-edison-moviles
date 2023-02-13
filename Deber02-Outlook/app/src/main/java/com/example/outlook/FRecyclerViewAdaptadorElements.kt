package com.example.outlook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorElements (
    private val contexto: GRecyclerView,
    private val lista: ArrayList<Correo>,
    private val recyclerView: RecyclerView
    ) : RecyclerView.Adapter<FRecyclerViewAdaptadorElements.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emisorTextView: TextView
        val receptorTextView: TextView
        val mensajeTextView: TextView

        init {
            emisorTextView = view.findViewById(R.id.rc_tv_emisor)
            receptorTextView = view.findViewById(R.id.rc_tv_receptor)
            mensajeTextView = view.findViewById(R.id.rc_tv_mensaje)

        }

    }
    // Setear el Layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    // Setear los datos para la iteración
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mensajeActual = this.lista[position]
        holder.emisorTextView.text = mensajeActual.emisor
        holder.receptorTextView.text = mensajeActual.receptor
        holder.mensajeTextView.text = mensajeActual.mensaje

    }
    // tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}