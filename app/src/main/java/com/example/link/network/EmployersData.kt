package com.example.link

import com.squareup.moshi.Json

data class EmployersData(
    @Json(name = "per_page")  val perPage:Int,
    @Json(name = "page")  val page:Int,
    @Json(name = "pages")  val pages:Int,
    @Json(name = "found")  val found:Int,
    @Json(name = "items")  val items:List<Item>
)
data class Item(
     val id:String,
     val name:String,
     val vacancies_url:String,
     val open_vacancies:Int
)