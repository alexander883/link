package com.example.link.network

import com.squareup.moshi.Json

data class SingleEmployerData(
    @Json(name = "name")  val name:String,
    @Json(name = "type")  val type:String?,
    @Json(name = "id")  val id:String,
    @Json(name = "site_url")  val siteUrl:String,
    @Json(name = "open_vacancies")  val openVacancies:String,
    @Json(name = "logo_urls")  val logoUrls:Logo?
)
data class Logo(
    @Json(name = "90")  val extension90:String,
    @Json(name = "240")  val extension240:String,
    @Json(name = "original")  val original:String
)