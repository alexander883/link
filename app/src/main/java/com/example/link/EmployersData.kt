package com.example.link

import com.squareup.moshi.Json

data class EmployersData(
    @Json(name = "items")  val items:List<Item>
)
data class Item(
    @Json(name = "name") val name:String
)