package com.example.link

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


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
        Log.i("LOG", " $data")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val text1: TextView = itemView.findViewById(R.id.text1)

        private val posit: TextView =itemView.findViewById(R.id.posit)

        fun bind(item: Item, position: Int) {
            text1.text =item.name.toString()

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