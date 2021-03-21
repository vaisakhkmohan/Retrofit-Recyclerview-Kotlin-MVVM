package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.logging.Filter

interface DataService {

    @GET("destination")
    fun getDatalist(@QueryMap filter: HashMap<String, String>): Call<List<DataModel>>
}