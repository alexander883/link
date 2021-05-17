package com.example.link

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.http.Query

class HhApiViewModel: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status
init {
    getHh()
    _status.value="ничего"
}


    private fun getHh() {
        viewModelScope.launch {
           // _status.value = HhApiStatus.LOADING
            try {
                val listResult = HhApi.retrofitService.getEmployers().size
                _status.value = "Success: ${listResult} "
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}
