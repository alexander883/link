package com.example.link

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL ="https://api.hh.ru/"
const val request="employers"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()





interface HhApiService {
    @GET(request)
    suspend fun getEmployers(@Query("text") text:String):EmployersData
}

object HhApi {
    val retrofitService : HhApiService by lazy {
        retrofit.create(HhApiService::class.java) }
}
/*

/////////////////////////////////////////////////////////////
private val retrofit = Retrofit.Builder()
 .addConverterFactory(ScalarsConverterFactory.create())
 .baseUrl(BASE_URL)
 .build()

interface HhApiService {
 @GET(request)
 suspend fun getEmployers(@Query("text") text:String):String
}

//suspend fun getEmployers(@Query("ИМЯ дополнительного параметра") text:String):String
object HhApi {
 val retrofitService : HhApiService by lazy {
     retrofit.create(HhApiService::class.java) }
}
*/
/////{"items":[{"id":"999","name":}{}
