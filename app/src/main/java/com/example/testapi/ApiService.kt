package com.example.testapi

import com.example.testapi.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api")
    fun getGenres(): Call<Users>
}