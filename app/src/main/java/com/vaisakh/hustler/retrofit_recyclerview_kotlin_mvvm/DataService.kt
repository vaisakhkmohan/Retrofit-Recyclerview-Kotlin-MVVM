package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface DataService {

    //get All data from destination
    @GET("destination")
    fun getDatalist(@QueryMap filter: HashMap<String, String>): Call<List<DataModel>>

    //get data where id = "id"
    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<DataModel>

    //post some data to url
    @POST("destination")
    fun addDestination(@Body newDestination: DataModel): Call<DataModel>

    //update data where id = "id"
    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(
            @Path("id") id: Int,
            @Field("city") city: String,
            @Field("description") desc: String,
            @Field("country") country: String
    ): Call<DataModel>

    //delete data where id = "id"
    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Unit>
}
