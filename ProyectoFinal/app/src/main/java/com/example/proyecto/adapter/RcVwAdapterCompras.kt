package com.example.proyecto.adapter

import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.ListCompraUser
import com.example.proyecto.R
import com.example.proyecto.data.entity.Compra
import com.example.proyecto.data.firebase.FirebaseGlobal

class RcVwAdapterCompras(
    private val parentContext: ListCompraUser,
    private val list: ArrayList<Compra>
): RecyclerView.Adapter<RcVwAdapterCompras.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val cantidadTextView: TextView
        val totalTextView: TextView
        val photoImagenView: ImageView

        //Agrega fecha
        val fechaTextView: TextView

        init {
            nombreTextView = view.findViewById(R.id.rv_carrito_nombre_instrument)
            cantidadTextView = view.findViewById(R.id.rv_carrito_cantidad)
            totalTextView = view.findViewById(R.id.rv_carrito_total)

            photoImagenView = view.findViewById(R.id.rv_carrito_img)
            fechaTextView = view.findViewById(R.id.rv_carrito_compra_fecha)

            itemView.isClickable = true
            itemView.isLongClickable = true

            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
            itemView.setBackgroundResource(typedValue.resourceId)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_carrito,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val compra = this.list[position]

        holder.cantidadTextView.text = compra.cantidad.toString()
        holder.totalTextView.text = "$" +compra.total.toString()
        holder.fechaTextView.text = compra.fecha

        FirebaseGlobal.firebaseInstruments.read(compra.codeInstrument){
                instrument ->
            holder.nombreTextView.text = instrument.nombre.toString()
            Glide.with(holder.photoImagenView.context).load(instrument.img).into(holder.photoImagenView)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}