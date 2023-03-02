package com.example.adle_exam_2b

import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.entity.ComponentEntity

class ComponentRecyclerViewAdapter{/*(
    private val parentContext: ComponentList,
    private val list: ArrayList<ComponentEntity>
): RecyclerView.Adapter<ComponentRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val codeTextView: TextView
        val categoryTextView: TextView
        val descriptionTextView: TextView
        val discontinuedTextView: TextView
        val parentCodeTextView: TextView

        init {
            codeTextView = view.findViewById(R.id.tv_component_code)
            categoryTextView = view.findViewById(R.id.tv_component_category)
            descriptionTextView = view.findViewById(R.id.tv_component_description)
            discontinuedTextView = view.findViewById(R.id.tv_component_discontinued)
            parentCodeTextView = view.findViewById(R.id.tv_component_device_code)

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
        holder.categoryTextView.text = currentComponent.category
        holder.descriptionTextView.text = currentComponent.description
        holder.discontinuedTextView.text = currentComponent.discontinued.toString()
        holder.parentCodeTextView.text = currentComponent.deviceCode.toString()
    }

    override fun getItemCount(): Int {
        return this.list.size
    }*/
}
