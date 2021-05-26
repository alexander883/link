package com.example.link

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.link.adapter.EmployersAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>) {
    val adapter = recyclerView.adapter as EmployersAdapter
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

