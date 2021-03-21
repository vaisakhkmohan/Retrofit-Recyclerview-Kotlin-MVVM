package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import android.content.Context
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Api(context2: Context){

    var context : Context =context2

    interface FetchListener {
            fun onFetched(result: Response<List<DataModel>>, error: String?)
    }

    fun loadDestinations(listener: FetchListener) {
        val destinationService = ServiceBuilder.buildService(DataService::class.java)
        val filter = HashMap<String, String>()
        // filter["country"] = "India"
        val requestCall = destinationService.getDatalist(filter)
        requestCall.enqueue(object: Callback<List<DataModel>> {

            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                if (response.isSuccessful) {
                    listener.onFetched(response, "")
                } else if(response.code() == 401) {
                    Toast.makeText(context, "Your session has expired. Please Login again.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Failed to retrieve items", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                Toast.makeText(context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

}
