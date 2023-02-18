package com.example.outlook

import android.view.*
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterSeccion(private val secciones:ArrayList<Seccion>) :

    RecyclerView.Adapter<RecyclerViewAdapterSeccion.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        val nameseccion: TextView
        var idItemSeleccionado=0
        init{
            nameseccion=itemView.findViewById(R.id.rv_seccion_name)
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            val inflater = MenuInflater(v?.context)
            inflater.inflate(R.menu.menu_seccion, menu)
            menu?.setHeaderTitle("Opciones")

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