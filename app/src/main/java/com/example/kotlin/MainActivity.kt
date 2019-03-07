package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity(), interface1.f1 {
    override fun json(js: JSONObject) {

    }

    override fun onc(str: Int, gdh: String) {
        var str1 = str
        Log.d("str", str.toString())
    }


    lateinit var rec: RecyclerView
    var atlest = arrayListOf<String>("STr", "str1", "STr2")

    lateinit var coordinator: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Main2Activity.zoo()
        rec = findViewById(R.id.recycler)
        coordinator = findViewById(R.id.coordinator) as ConstraintLayout
        simpleone(View(this))
        argumentValid(45)



        rec.layoutManager = GridLayoutManager(this, 2)
        val usera = ArrayList<user>()
        usera.add(user("BALA", "Sankar"))
        usera.add(user("BALA", "Sankar"))
        usera.add(user("BALA", "Sankar"))
        usera.add(user("BALA", "Sankar"))
        val adapter = recycleradapter(usera, this)
        rec.adapter = adapter
        Log.d("arraylist", atlest.get(0))
    }

    fun simpleone(v: View) {
        var snackbar: Snackbar = Snackbar.make(coordinator, "snack", Snackbar.LENGTH_LONG)
            .setAction("retry", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    var str: String = "sfsg"
                    Log.d("snack", "sna" + str)
                    val intent=Intent(this@MainActivity,Main2Activity::class.java)
                    startActivity(intent)

                }

            })
        snackbar.show()
    }

    fun argumentValid(num: Int, str: String = "12") {

        Log.d("number", str + num)
    }
}
