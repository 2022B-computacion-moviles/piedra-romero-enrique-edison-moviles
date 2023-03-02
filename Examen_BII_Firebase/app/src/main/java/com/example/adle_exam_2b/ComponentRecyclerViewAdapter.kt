package com.example.adle_exam_2b

import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.entity.ComponentEntity

class ComponentRecyclerViewAdapter(
    private val parentContext: ComponentList,
    private val list: ArrayList<ComponentEntity>
): RecyclerView.Adapter<ComponentRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val codeTextView: TextView
        val parentCodeTextView: TextView

        val marcaTextView: TextView
        val fecha_elaboracionTextView: TextView
        val precioTextView: TextView
        val colorTextView: TextView
        val mesesTextView: TextView



        init {
            codeTextView = view.findViewById(R.id.tv_component_code)
            parentCodeTextView = view.findViewById(R.id.tv_component_device_code)

            marcaTextView = view.findViewById(R.id.tv_component_marca)
            fecha_elaboracionTextView = view.findViewById(R.id.tv_component_fecha)
            precioTextView = view.findViewById(R.id.tv_component_precio)
            colorTextView = view.findViewById(R.id.tv_component_color)
            mesesTextView = view.findViewById(R.id.tv_component_meses)

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
                inflater.inflate(R.menu.component_menu, menu)

                parentContext.setSelectedComponentCode(list[adapterPosition].code)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_component,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentComponent = this.list[position]

        holder.codeTextView.text = currentComponent.code.toString()
        holder.parentCodeTextView.text = currentComponent.deviceCode.toString()

        holder.marcaTextView.text = currentComponent.marca
        holder.fecha_elaboracionTextView.text = currentComponent.fecha_elaboracion.toString()
        holder.precioTextView.text = currentComponent.precio.toString()
        holder.colorTextView.text = currentComponent.color_subjetivo.toString()
        holder.mesesTextView.text = currentComponent.meses_plazo_pagar.toString()

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}
