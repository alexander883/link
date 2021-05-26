package com.example.link.network

import com.example.link.EmployersData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
///////////////https://github.com/hhru/api
////https://github.com/hhru/api/blob/master/docs/employers.md#search
private const val BASE_URL ="https://api.hh.ru/"
private const val request="employers/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HhEmployersApiService {
    @GET(request)
    suspend fun getEmployers(
        @Query("text") text:String,
        @Query("per_page") per_page:String,
        @Query("page") page:String
    ): EmployersData
}

object HhEmployersApi {
    val retrofitService : HhEmployersApiService by lazy {
        retrofit.create(HhEmployersApiService::class.java) }
}

//////////////////////////////////////////////


interface HhSingleEmployerApiService {
    @GET("$request{employer_id}")
    suspend fun getEmployer(@Path("employer_id") id: String,
      ): SingleEmployerData
}

object HhSingleEmployerApi {
    val retrofitService : HhSingleEmployerApiService by lazy {
        retrofit.create(HhSingleEmployerApiService::class.java) }
}

/////{"items":[{"id":"999","name":}{}
