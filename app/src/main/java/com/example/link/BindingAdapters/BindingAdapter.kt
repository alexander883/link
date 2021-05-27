package com.example.link

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.link.adapter.EmployersAdapter
import com.example.link.viewmodel.HhEmployersApiStatus


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
        imgView.load(imgUri){
              placeholder(R.drawable.load_img)
             error(R.drawable.error_img)
        }

    }
}

@BindingAdapter("HhEmpoyersApiStatus")
fun bindStatus(statusImageView: ImageView, status: HhEmployersApiStatus?) {
    when (status) {
        HhEmployersApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.load_img)
        }
        HhEmployersApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.error_img)
        }
        HhEmployersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
@BindingAdapter("StatusConstraintLayout")
fun bindConstraintLayout(view: View, status: HhEmployersApiStatus?) {
    when (status) {
        HhEmployersApiStatus.LOADING,  HhEmployersApiStatus.ERROR ->{
            view.visibility=View.INVISIBLE
        }
        HhEmployersApiStatus.DONE->{
            view.visibility=View.VISIBLE
        }
    }
}