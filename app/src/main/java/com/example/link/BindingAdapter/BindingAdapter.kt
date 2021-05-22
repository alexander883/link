package com.example.link

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.link.adapter.HhAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>) {
    val adapter = recyclerView.adapter as HhAdapter
    adapter.data=data
}
@BindingAdapter("android:visibility")
fun bindCheckButtonBack(view: View, visibilityButtonBack: Boolean) {
    view.visibility = if (visibilityButtonBack) View.VISIBLE else View.GONE
}
@BindingAdapter("android:visibility")
fun bindCheckButtonForward(view: View, visibilityButtonForward: Boolean) {
    view.visibility = if (visibilityButtonForward) View.VISIBLE else View.GONE
}