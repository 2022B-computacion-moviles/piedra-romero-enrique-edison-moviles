package com.example.adle_exam_2b

import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.entity.Concesionario

class RcVwAdapterConcesionario(
    private val parentContext: ListConcesionario,
    private val list: ArrayList<Concesionario>
): RecyclerView.Adapter<RcVwAdapterConcesionario.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val codeTextView: TextView
        val nombreTextView: TextView
        val fecha_inaguracionTextView: TextView
        val porcentajeTextView: TextView
        val cantEmpleadosTextView: TextView

        init {
            codeTextView = view.findViewById(R.id.tv_device_code)
            nombreTextView = view.findViewById(R.id.tv_device_nombre)
            fecha_inaguracionTextView = view.findViewById(R.id.tv_device_fecha)
            porcentajeTextView = view.findViewById(R.id.tv_device_porcentaje)
            cantEmpleadosTextView = view.findViewById(R.id.tv_device_empleados)

            view.setOnCreateContextMenuListener(this)

            // Setting the view selection mode
            itemView.isClickable = true
            itemView.isLongClickable = true

            // Setting the style
            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
            itemView.setBackgroundResource(typedValue.resourceId)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, view: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            if (menu != null) {
                val inflater = MenuInflater(view?.context)
                inflater.inflate(R.menu.device_menu, menu)

                parentContext.setSelectedDeviceCode(list[adapterPosition].code)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_device,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentDevice = this.list[position]

        holder.codeTextView.text = currentDevice.code.toString()
        holder.nombreTextView.text = currentDevice.nombre
        holder.fecha_inaguracionTextView.text = currentDevice.fecha_inaguracion.toString()
        holder.porcentajeTextView.text = currentDevice.porcentaje_personas_satisfechas.toString()
        holder.cantEmpleadosTextView.text = currentDevice.cantidad_empleados.toString()
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}