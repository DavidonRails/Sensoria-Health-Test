package com.kabira.boredapp.apiServices

import com.kabira.boredapp.models.CardModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpointInterface {
    @GET("api/activity")
    fun getCards(): Call<CardModel>
}