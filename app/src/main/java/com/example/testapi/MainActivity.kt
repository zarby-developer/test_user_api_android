package com.example.testapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapi.model.Users
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.testapi.model.Result
class MainActivity : AppCompatActivity() {
    val TAG = "MiApp"
    var data : ArrayList<Result> = ArrayList()

    lateinit var adapterR: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiRest.initService()
        getGenres()
        adapterR = UsersAdapter(data)
        mainRecicler.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        mainRecicler.adapter = adapterR

// Recycler

    }
    private fun getGenres() {
        val call  = ApiRest.service.getGenres()
        call.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val body  = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    data.clear()
                    data.addAll(body.results)
                    adapterR.notifyDataSetChanged()
                }

// Imprimir aqui el listado
                else {
                    Log.e(TAG, response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }
}


