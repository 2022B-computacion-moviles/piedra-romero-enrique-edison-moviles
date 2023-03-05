package com.example.proyecto.adapter

import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.Home
import com.example.proyecto.ListCarritoUser
import com.example.proyecto.R
import com.example.proyecto.data.entity.Carrito
import com.example.proyecto.data.entity.Instrument

class RcVwAdapterCarritos(
    private val parentContext: ListCarritoUser,
    private val list: ArrayList<Carrito>
): RecyclerView.Adapter<RcVwAdapterCarritos.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val codeTextView: TextView
        val cantidadTextView: TextView
        val totalTextView: TextView
        //val photoImagenView: ImageView

        init {
            codeTextView = view.findViewById(R.id.rv_carrito_code_instrument)
            cantidadTextView = view.findViewById(R.id.rv_carrito_cantidad)
            totalTextView = view.findViewById(R.id.rv_carrito_total)

            //photoImagenView = view.findViewById(R.id.rv_instrument_img)

            view.setOnCreateContextMenuListener(this)


            itemView.isClickable = true
            itemView.isLongClickable = true

            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
            itemView.setBackgroundResource(typedValue.resourceId)


        }

        override fun onCreateContextMenu(menu: ContextMenu?, view: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            if (menu != null) {
                val inflater = MenuInflater(view?.context)
                inflater.inflate(R.menu.menu_carrito, menu)

                parentContext.setSelectedCarrito(list[adapterPosition])
            }
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
        val carrito = this.list[position]

        holder.codeTextView.text = carrito.codeInstrument.toString()
        holder.cantidadTextView.text = carrito.cantidad.toString()
        holder.totalTextView.text = "$" +carrito.total.toString()

        //Glide.with(holder.photoImagenView.context).load(instrument.img).into(holder.photoImagenView)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}