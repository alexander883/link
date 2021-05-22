package com.example.link

import com.squareup.moshi.Json

data class EmployersData(
    @Json(name = "items")  val items:List<Item>,
    @Json(name = "found")  val found:Int,
    @Json(name = "page")  val page:Int
)
data class Item(
     val name:String
)