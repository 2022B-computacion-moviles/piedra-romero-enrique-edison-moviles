package com.example.outlook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.outlook.R
import com.example.outlook.Seccion

class SeccionAdapter(private val seccionList: List<Seccion>,private val onClickListener: (Seccion) -> Unit): RecyclerView.Adapter<SeccionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeccionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SeccionViewHolder(layoutInflater.inflate(R.layout.item_seccion, parent, false))
    }

    override fun onBindViewHolder(holder: SeccionViewHolder, position: Int) {
        val item = seccionList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = seccionList.size
}