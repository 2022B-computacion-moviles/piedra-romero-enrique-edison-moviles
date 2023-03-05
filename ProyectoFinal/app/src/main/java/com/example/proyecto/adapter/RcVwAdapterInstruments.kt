package com.example.proyecto.adapter

import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto.Home
import com.example.proyecto.R
import com.example.proyecto.data.entity.Instrument

class RcVwAdapterInstruments(
    private val parentContext: Home,
    private val list: ArrayList<Instrument>,
    private val onClickListener: (instrument: Instrument) -> Unit
): RecyclerView.Adapter<RcVwAdapterInstruments.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        //val codeTextView: TextView
        val nombreTextView: TextView
        val tipoTextView: TextView
        //val descripcionTextView: TextView
        //val stockTextView: TextView
        val precioTextView: TextView
        val photoImagenView: ImageView

        init {
            //codeTextView = view.findViewById(R.id.rv_instrument_code)
            nombreTextView = view.findViewById(R.id.rv_instrument_nombre)
            tipoTextView = view.findViewById(R.id.rv_instrument_tipo)
            //descripcionTextView = view.findViewById(R.id.rv_instrument_descripcion)
            //stockTextView = view.findViewById(R.id.rv_instrument_stock)
            precioTextView = view.findViewById(R.id.rv_instrument_precio)
            photoImagenView = view.findViewById(R.id.rv_instrument_img)

            view.setOnCreateContextMenuListener(this)


            itemView.isClickable = true
            itemView.isLongClickable = true

            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
            itemView.setBackgroundResource(typedValue.resourceId)


            itemView.setOnClickListener { // Configuración del clic en el elemento
                onClickListener(list[adapterPosition])
            }
        }

        override fun onCreateContextMenu(menu: ContextMenu?, view: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            if (menu != null) {
                val inflater = MenuInflater(view?.context)
                inflater.inflate(R.menu.menu_instrument, menu)

                parentContext.setSelectedInstrumentCode(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_instrument,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val instrument = this.list[position]

        //holder.codeTextView.text = instrument.codeInstrument.toString()
        holder.nombreTextView.text = instrument.nombre
        holder.tipoTextView.text = instrument.tipo
        //holder.descripcionTextView.text = instrument.descripcion
        //holder.stockTextView.text = instrument.stock.toString()
        holder.precioTextView.text = "$" +instrument.precio.toString()
        //holder.imgTextView.text = instrument.img
        Glide.with(holder.photoImagenView.context).load(instrument.img).into(holder.photoImagenView)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}