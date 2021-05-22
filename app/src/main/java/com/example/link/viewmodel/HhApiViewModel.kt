package com.example.link.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.link.HhApi
import com.example.link.Item
import kotlinx.coroutines.launch
private const val perPage=100
class HhApiViewModel: ViewModel() {

    private val _employers = MutableLiveData<List<Item>>()
    val status: LiveData<List<Item>> = _employers

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _countFound= MutableLiveData<Int>()
    val countFound: LiveData<Int> = _countFound

    private val _visibilityButtonBack= MutableLiveData<Boolean>()
    val visibilityButtonBack: LiveData<Boolean> =_visibilityButtonBack
    private val _visibilityButtonForward= MutableLiveData<Boolean>()
    val visibilityButtonForward: LiveData<Boolean> =_visibilityButtonForward

    private val _currentPage= MutableLiveData<Int>()
    val currentPage: LiveData<Int> =_currentPage
    private val _totalPages= MutableLiveData<Int>()
    val totalPages: LiveData<Int> =_totalPages

   // var allPages=MutableLiveData<Int>()

init {
   // getHh()
    _employers.value= arrayListOf<Item>()
    _visibilityButtonBack.value=false
    _visibilityButtonForward.value=true
    _currentPage.value=1
    _totalPages.value=1
}
 fun setSearch(text:String){
     _search.value=text
 }


    fun getHh() {
        viewModelScope.launch {
           // _status.value = HhApiStatus.LOADING
            try {
                val currentPage=currentPage.value!!-1
                val listResult = HhApi.retrofitService.getEmployers(
                    search.value!!, perPage.toString(), currentPage.toString())

                _employers.value = listResult.items
                _countFound.value=listResult.found
                //находим кол-во страниц
                if (countFound.value!!% perPage==0) {
                    _totalPages.value = countFound.value!! / perPage
                }
                else{
                    _totalPages.value =(countFound.value!! / perPage)+1
                }

               // _status.value = "Success: ${listResult} "
               // _status.value = "Success: ${listResult.items[5]} "
            } catch (e: Exception) {
               // _status.value = "Failure: ${e.message}"
            }
        }
    }

}


