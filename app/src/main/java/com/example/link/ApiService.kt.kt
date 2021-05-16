package com.example.link

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://api.hh.ru/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(): List<String>
}


object MarsApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
