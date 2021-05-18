package com.example.link

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.link.adapter.HhAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>?) {
    val adapter = recyclerView.adapter as HhAdapter
    adapter.data
}