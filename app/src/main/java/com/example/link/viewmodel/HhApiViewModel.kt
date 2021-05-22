package com.example.link.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.link.HhApi
import com.example.link.Item
import kotlinx.coroutines.launch

class HhApiViewModel: ViewModel() {

    private val _status = MutableLiveData<List<Item>>()
    val status: LiveData<List<Item>> = _status

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _countFound= MutableLiveData<String>()
    val countFound: LiveData<String> = _countFound

    private val _visibilityButtonBack= MutableLiveData<Boolean>()
    val visibilityButtonBack: LiveData<Boolean> =_visibilityButtonBack
    private val _visibilityButtonForward= MutableLiveData<Boolean>()
    val visibilityButtonForward: LiveData<Boolean> =_visibilityButtonForward

    val countPages= MutableLiveData<Int>()

init {
   // getHh()
    _status.value= arrayListOf<Item>()
    _visibilityButtonBack.value=true
    _visibilityButtonForward.value=true
}
 fun setSearch(text:String){
     _search.value=text
 }


    fun getHh() {
        viewModelScope.launch {
           // _status.value = HhApiStatus.LOADING
            try {
                val listResult = HhApi.retrofitService.getEmployers(search.value!!, "100", "0")
                _status.value = listResult.items
                _countFound.value=listResult.found.toString()
                countPages.value=listResult.pages
               // _status.value = "Success: ${listResult} "
               // _status.value = "Success: ${listResult.items[5]} "
            } catch (e: Exception) {
               // _status.value = "Failure: ${e.message}"
            }
        }
    }

}
