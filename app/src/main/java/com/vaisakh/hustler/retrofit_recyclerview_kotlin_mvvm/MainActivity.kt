package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm.Api.FetchListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        LoadData()
    }

    private fun LoadData() {
        Api(this@MainActivity).loadDestinations(object : FetchListener {
            override fun onFetched(result: Response<List<DataModel>>, error: String?) {
                if (error.equals("")){
                    val destinationList = result.body()!!
                    recyclerview.adapter = DataAdapter(destinationList)
                }
            }
        })
    }

    private fun loadSingleData() {
        Api(this@MainActivity).loadSingleData(4, object : Api.Fetchmodeldata {
            override fun onmodeldata(result: Response<DataModel>, error: String?) {
                if (error.equals("")) {
                    val destination = result.body()
                    destination?.let {
                        Log.e("TAG", "onmodeldata:  ${destination.city}")
                    }
                }
            }
        })
    }

    private fun addSingledata() {
        Api(this@MainActivity).AddSingleData("thrissur","your data","india", object : Api.Fetchmodeldata {
            override fun onmodeldata(result: Response<DataModel>, error: String?) {
                if (error.equals("")) {
                    val destination = result.body()
                    destination?.let {
                        Toast.makeText(this@MainActivity, "Item Updated Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun updateSingledata() {
        Api(this@MainActivity).UpdateSingleData(4, object : Api.Fetchmodeldata {
            override fun onmodeldata(result: Response<DataModel>, error: String?) {
                if (error.equals("")) {
                    val destination = result.body()
                    destination?.let {
                        Toast.makeText(this@MainActivity, "Item Updated Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun DeleteSingleData() {
        Api(this@MainActivity).DeleteSingleData(4, object : Api.Fetchmodeldata {
            override fun onmodeldata(result: Response<DataModel>, error: String?) {
                if (error.equals("")) {
                        Toast.makeText(this@MainActivity, "Item Updated Successfully", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
