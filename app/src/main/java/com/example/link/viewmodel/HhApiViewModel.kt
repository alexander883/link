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

init {
   // getHh()
    _status.value= listOf(Item("идет загрузка"))
}
 fun setSearch(text:String){
     _search.value=text
 }


    fun getHh() {
        viewModelScope.launch {
           // _status.value = HhApiStatus.LOADING
            try {
                val listResult = HhApi.retrofitService.getEmployers(search.value!!)
                _status.value = listResult.items
               // _status.value = "Success: ${listResult} "
               // _status.value = "Success: ${listResult.items[5]} "
            } catch (e: Exception) {
               // _status.value = "Failure: ${e.message}"
            }
        }
    }

}
