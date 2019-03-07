package com.example.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class Maind3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Steacl success", Toast.LENGTH_SHORT).show()
    }
}
