package com.example.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class recycleradapter(var userlis: ArrayList<user>, val contect: Context) :
    RecyclerView.Adapter<recycleradapter.viewholder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.recycleritem, p0, false)
        return viewholder(v)
    }

    override fun getItemCount(): Int {
        return userlis.size
    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {
        val user1: user = userlis[p1]
        p0?.name.text = user1.name
        p0?.address.text = userlis.get(p1).address
        if (p1 == 0) {
            p0.name.setTextColor(contect.resources.getColor(R.color.colorAccent))
        }
        p0.name.setOnClickListener {
             Log.d("userlis", userlis.toString())
        }
    }


    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.textView) as TextView
        val address = itemView.findViewById(R.id.address) as TextView
    }
}