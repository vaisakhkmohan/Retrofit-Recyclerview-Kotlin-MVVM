package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import android.content.Context
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Api(context2: Context){
    lateinit var context : Context
    lateinit var  destinationService : DataService
    init {
         context  = context2
         destinationService = ServiceBuilder.buildService(DataService::class.java)
    }
    interface FetchListener {
            fun onFetched(result: Response<List<DataModel>>, error: String?)
    }

    interface Fetchmodeldata {
        fun onmodeldata(result: Response<DataModel>, error: String?)
    }

    fun loadDestinations(listener: FetchListener) {
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

    fun loadSingleData(id: Int,listener: Fetchmodeldata) {
        val requestCall = destinationService.getDestination(id)
        requestCall.enqueue(object : retrofit2.Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                        listener.onmodeldata(response, "")
                    }  else {
                        Toast.makeText(context, "Failed to retrieve items", Toast.LENGTH_LONG).show()
                    }
                }
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(context, "Failed to retrieve details " + t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


     fun UpdateSingleData(id: Int,listener: Fetchmodeldata) {
            val city = "Thrissur"
            val description = "your own data"
            val country = "india"
            val requestCall = destinationService.updateDestination(id, city, description, country)
            requestCall.enqueue(object: Callback<DataModel> {
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    if (response.isSuccessful) {
                        listener.onmodeldata(response, "")
                    } else {
                        Toast.makeText(context, "Failed to update item", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    Toast.makeText(context, "Failed to update item", Toast.LENGTH_SHORT).show()
                }
            })
    }

     fun DeleteSingleData(id: Int,listener: Fetchmodeldata) {

            val requestCall = destinationService.deleteDestination(id)
            requestCall.enqueue(object: Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show()
                }
            })
        }

    fun AddSingleData(city: String, des: String, country: String , listener: Fetchmodeldata){
        val newDestination = DataModel()
        newDestination.city = city
        newDestination.description = des
        newDestination.country = country
        val requestCall = destinationService.addDestination(newDestination)
        requestCall.enqueue(object: Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    listener.onmodeldata(response,"" )
                    var newlyCreatedDestination = response.body() // Use it or ignore it
                    Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
