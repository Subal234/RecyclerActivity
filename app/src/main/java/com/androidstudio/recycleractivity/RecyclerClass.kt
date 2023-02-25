package com.androidstudio.recycleractivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerClass (var array: ArrayList<StudentInfo>,var recyclerInterface: RecyclerInterface) : RecyclerView.Adapter<RecyclerClass.ViewHolder> (){
    class ViewHolder(var view: View): RecyclerView.ViewHolder(view) {

        var tvEntername : TextView = view.findViewById(R.id.tvname)
        var tvEnterPhone : TextView = view.findViewById(R.id.tvphone)
        var tvAddress : TextView = view.findViewById(R.id.tvaddress)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var initview = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)

        return ViewHolder(initview)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvEntername.setText(array[position].name.toString())
        holder.tvEnterPhone.setText(array[position].phone.toString())
        holder.tvAddress.setText(array[position].address.toString())
        holder.view.setOnClickListener { recyclerInterface.onUpdateClick(array[position],position) }


    }

    override fun getItemCount(): Int {
        return array.size

    }

}