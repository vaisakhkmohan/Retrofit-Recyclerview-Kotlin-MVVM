package com.vaisakh.hustler.retrofit_recyclerview_kotlin_mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DataAdapter (private val Arg_datalist: List<DataModel>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.datamodel = Arg_datalist[position]
        holder.textdata.text =Arg_datalist[position].city
        holder.country.text = Arg_datalist[position].country
        holder.discription.text = Arg_datalist[position].description
        holder.itemView.setOnClickListener {
                 val context = it.context
            Toast.makeText(context,Arg_datalist[position].city,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
         return Arg_datalist.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textdata : TextView = itemView.findViewById(R.id.data)
        val country : TextView = itemView.findViewById(R.id.country)
        val discription : TextView = itemView.findViewById(R.id.discription)
        var datamodel : DataModel ?= null
        override fun toString(): String {
            return """${super.toString()} '${textdata.text}'"""
        }
    }

}