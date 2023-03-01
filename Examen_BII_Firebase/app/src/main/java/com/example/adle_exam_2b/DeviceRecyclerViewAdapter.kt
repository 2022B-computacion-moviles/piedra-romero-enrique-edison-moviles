package com.example.adle_exam_2b

import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adle_exam_2b.data.entity.DeviceEntity

class DeviceRecyclerViewAdapter(
    private val parentContext: DeviceList,
    private val list: ArrayList<DeviceEntity>
): RecyclerView.Adapter<DeviceRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val codeTextView: TextView
        val nameTextView: TextView
        val categoryTextView: TextView
        val dateTextView: TextView
        val priceTextView: TextView

        init {
            codeTextView = view.findViewById(R.id.tv_device_code)
            nameTextView = view.findViewById(R.id.tv_device_name)
            categoryTextView = view.findViewById(R.id.tv_device_category)
            dateTextView = view.findViewById(R.id.tv_device_date)
            priceTextView = view.findViewById(R.id.tv_device_price)

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
        holder.nameTextView.text = currentDevice.name
        holder.categoryTextView.text = currentDevice.category
        holder.dateTextView.text = currentDevice.releaseDate.toString()
        holder.priceTextView.text = currentDevice.price.toString()
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}