package com.example.steam_store_app.core.service

import AppIdList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {
    @GET("api/appdetails/")
    fun getDetails(
        @Query("appids") id: Int
    ): Call<AppIdList>

}