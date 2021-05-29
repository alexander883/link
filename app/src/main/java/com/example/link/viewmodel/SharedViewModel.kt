package com.example.link.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.link.Item
import com.example.link.network.HhEmployersApi
import com.example.link.network.HhSingleEmployerApi
import kotlinx.coroutines.launch

private const val perPage=100//количество позиций на одну запрашиваемую страницу
enum class HhEmployersApiStatus { LOADING, ERROR, DONE }

class SharedViewModel: ViewModel() {
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

    private val _statusEmployers = MutableLiveData<HhEmployersApiStatus >()
    val statusEmployers: LiveData<HhEmployersApiStatus > = _statusEmployers

    private var _statusSingleEmpoyer= MutableLiveData<String>()
    val status: LiveData<String> =_statusSingleEmpoyer

    private var _vacancies= MutableLiveData<String>()
    val vacancies: LiveData<String> =_vacancies
    private var _nameEmpoyer= MutableLiveData<String>()
    val nameEmpoyer: LiveData<String> =_nameEmpoyer
    private var _typeEmpoyer= MutableLiveData<String>()
    val typeEmpoyer: LiveData<String> =_typeEmpoyer
    private var _siteEmpoyer= MutableLiveData<String>()
    val siteEmpoyer: LiveData<String> =_siteEmpoyer
    private var _logoUrl= MutableLiveData<String>()
    val logoUrl: LiveData<String> =_logoUrl
    init {
    _employers.value= arrayListOf<Item>()
    _visibilityButtonBack.value=false
    _visibilityButtonForward.value=false
    _currentPage.value=1
    _totalPages.value=1
    }

    fun setSearch(text:String){
     _search.value=text
    }

///получаем список компаний
    fun getHhEmployers() {
        viewModelScope.launch {
            _statusEmployers.value=HhEmployersApiStatus.LOADING
            try {
                val page=currentPage.value!!-1
                val listResult = HhEmployersApi.retrofitService.getEmployers(
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
                _visibilityButtonForward.value = (totalPages.value!! > 1)and (currentPage.value!!<totalPages.value!!)

                _visibilityButtonBack.value = currentPage.value!! !=1
                _statusEmployers.value=HhEmployersApiStatus.DONE
            }
            catch (e: Exception) {
                _statusEmployers.value=HhEmployersApiStatus.ERROR
            }
        }
    }

    fun getHhSingleEmployer(id:String) {
        viewModelScope.launch {
            _statusSingleEmpoyer.value="Загружается..."
            try {
                val res= HhSingleEmployerApi.retrofitService.getEmployer(id)
                _vacancies.value=res.openVacancies
                _nameEmpoyer.value=res.name
                _typeEmpoyer.value=res.type
                _siteEmpoyer.value=res.siteUrl
                _logoUrl.value=res.logoUrls?.extension240
                _statusSingleEmpoyer.value=""
            }
            catch (e: Exception){
                _statusSingleEmpoyer.value= "Failure: ${e.message}"
            }
        }
    }

    fun clickForward(){
        _currentPage.value=currentPage.value!! + 1
        getHhEmployers()
    }

    fun clickBack(){
        _currentPage.value=currentPage.value!! - 1
        getHhEmployers()
    }

    fun reset(){
        _employers.value= arrayListOf<Item>()
        _visibilityButtonBack.value=false
        _visibilityButtonForward.value=false
        _currentPage.value=1
        _totalPages.value=1
    }
    fun  resetEmpoyer(){
        _nameEmpoyer.value=""
        _typeEmpoyer.value=""
        _vacancies.value=""
    }
}
