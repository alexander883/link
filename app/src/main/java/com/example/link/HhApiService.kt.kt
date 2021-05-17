package com.example.link

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://api.hh.ru/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

const val request="employers"



interface HhApiService {
    @GET(request)
    suspend fun getEmployers():Array<String>
}

object HhApi {
    val retrofitService : HhApiService by lazy {
        retrofit.create(HhApiService::class.java) }
}
/*


val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


const val request="employers"

interface HhApiService {
    @GET(request)
    suspend fun getEmployers(@Query("text") text:String):EmployersData
}

//suspend fun getEmployers(@Query("ИМЯ дополнительного параметра") text:String):String

object HhApi {
    val retrofitService : HhApiService by lazy {
        retrofit.create(HhApiService::class.java) }
}

 */