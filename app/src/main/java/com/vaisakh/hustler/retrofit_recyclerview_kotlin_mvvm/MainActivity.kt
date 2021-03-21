package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


}
