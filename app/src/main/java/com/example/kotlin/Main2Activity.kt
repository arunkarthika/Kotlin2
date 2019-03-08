package com.example.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Response

class Main2Activity : AppCompatActivity() {

    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        apiInterface = APIClient.client.create(APIInterface::class.java)


        val call = apiInterface.checkpta()
        call.enqueue(object : retrofit2.Callback<MultipleResource> {
            override fun onResponse(call: Call<MultipleResource>, response: Response<MultipleResource>) {


                Log.d("TAG", response.code().toString() + "")

                var displayResponse = ""

                val resource = response.body()
                val text = resource.page
                val total = resource.total
                val totalPages = resource.totalPages
                val datumList = resource.data

                displayResponse += "$text Page\n$total Total\n$totalPages Total Pages\n"

                for (datum in datumList) {
                    displayResponse += datum.id.toString() + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n"
                }

                textView2.setText(displayResponse)
                Toast.makeText(this@Main2Activity,displayResponse,Toast.LENGTH_LONG).show()
                Log.d("displayResponse",displayResponse);


            }

            override fun onFailure(call: Call<MultipleResource>, t: Throwable) {

                Log.d("error", t.toString())


                call.cancel()
            }
        })


    }

    companion object {
        fun zoo() {
            Log.d("Static Succes", "Static Succes")

        }
    }
}
