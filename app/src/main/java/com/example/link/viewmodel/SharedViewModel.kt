package com.example.link.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.link.EApi
import com.example.link.HhApi
import com.example.link.Item
import kotlinx.coroutines.launch

private const val perPage=100//количество позиций на одну запрашиваемую страницу

class HhApiViewModel: ViewModel() {
    ///cписок компаний, получаемых по запросу
    private val _employers = MutableLiveData<List<Item>>()
    val employers: LiveData<List<Item>> = _employers
    //искомая компания
    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search
    //число найденных компаний
    private val _countFound= MutableLiveData<Int>()
    val countFound: LiveData<Int> = _countFound
    //видимость кнопок
    private val _visibilityButtonBack= MutableLiveData<Boolean>()
    val visibilityButtonBack: LiveData<Boolean> =_visibilityButtonBack
    private val _visibilityButtonForward= MutableLiveData<Boolean>()
    val visibilityButtonForward: LiveData<Boolean> =_visibilityButtonForward
    //текущая отображаемая страница
    private val _currentPage= MutableLiveData<Int>()
    val currentPage: LiveData<Int> =_currentPage
    //всего страниц с компаниями
    private val _totalPages= MutableLiveData<Int>()
    val totalPages: LiveData<Int> =_totalPages

    private val _clickId= MutableLiveData<String>()
    val clickId: LiveData<String> =_clickId



    private var _inf= MutableLiveData<String>()
    val inf: LiveData<String> =_inf

init {
   // getHh()
    _employers.value= arrayListOf<Item>()
    _visibilityButtonBack.value=false
    _visibilityButtonForward.value=false
    _currentPage.value=1
    _totalPages.value=1

   // _inf.value=""
}



 fun setSearch(text:String){
     _search.value=text
 }


    fun getHh() {
        viewModelScope.launch {
           // _status.value = HhApiStatus.LOADING
            try {
                val page=currentPage.value!!-1
                val listResult = HhApi.retrofitService.getEmployers(
                    search.value!!, perPage.toString(), page.toString())

                _employers.value = listResult.items
                _countFound.value=listResult.found
                //находим кол-во страниц
                if (countFound.value!!% perPage==0) {
                    _totalPages.value = countFound.value!! / perPage
                }
                else{
                    _totalPages.value =(countFound.value!! / perPage)+1
                }
                _visibilityButtonForward.value = (totalPages.value!! > 1) and (currentPage.value!!<totalPages.value!!)

                _visibilityButtonBack.value = currentPage.value!! !=1


               // _status.value = "Success: ${listResult} "
               // _status.value = "Success: ${listResult.items[5]} "
            } catch (e: Exception) {
               // _status.value = "Failure: ${e.message}"
            }
        }
    }

    fun getEh() {
        viewModelScope.launch {
            try {
                val res= EApi.retrofitService.getEmployer()
                _inf.value=res.openVacancies
            }
        catch (e: Exception){
            _inf.value= "Failure: ${e.message}"
        }}}

    fun clickForward(){
        _currentPage.value=currentPage.value!! + 1
        getHh()
    }

    fun clickBack(){
        _currentPage.value=currentPage.value!! - 1
        getHh()
    }

    fun reset(){
        _employers.value= arrayListOf<Item>()
        _visibilityButtonBack.value=false
        _visibilityButtonForward.value=false
        _currentPage.value=1
        _totalPages.value=1
    }

//получаем Id выбранной по клику компании
    fun getId(position: Int){
        _clickId.value= employers.value?.get(position)?.id
   //     employers.value
    }

}
