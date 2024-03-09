package com.example.aviatickets.model.network


import com.example.aviatickets.model.entity.Offer
import retrofit2.Call
import retrofit2.http.GET

interface MyApi{
    @GET("offer")
    fun getOffers(): Call<List<Offer>>
}