package com.example.link

import com.squareup.moshi.Json


data class EmployersData(

    @Json(name = "items") val items: Array<String>
)