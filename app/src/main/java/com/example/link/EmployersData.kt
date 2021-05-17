package com.example.link

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class EmployersData(
    @Json(name="id") val name:String=""

)