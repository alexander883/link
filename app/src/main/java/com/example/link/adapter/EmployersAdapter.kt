package com.example.link.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.link.Item
import com.example.link.R

class EmployersAdapter(private  val listener: OnItemClickListener
): RecyclerView.Adapter<EmployersAdapter.ViewHolder>() {

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
        val itemView = LayoutInflater.from(parent.context).inflate(R. layout.found_item, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val employer: TextView = itemView.findViewById(R.id.employer)
        private val posit: TextView = itemView.findViewById(R.id.posit)
        private val countVac:TextView=itemView.findViewById(R.id.vacCount)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
        @SuppressLint("SetTextI18n")
        fun bind(item: Item, position: Int) {
            employer.text = item.name
            countVac.text="Вакансий: "+item.open_vacancies.toString()
            posit.text=(position+1).toString()
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}