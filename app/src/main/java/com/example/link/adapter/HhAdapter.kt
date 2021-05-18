package com.example.link.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater.from
import com.example.link.Item
import com.example.link.R


class HhAdapter: RecyclerView.Adapter<HhAdapter.ViewHolder>() {

    var data =  listOf<Item>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val t: TextView = itemView.findViewById(R.id.text1)

        private val posit: TextView =itemView.findViewById(R.id.posit)

        fun bind(item: Item, position: Int) {
            t.text =item.name

            posit.text=(position+1).toString()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R. layout.found_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}
