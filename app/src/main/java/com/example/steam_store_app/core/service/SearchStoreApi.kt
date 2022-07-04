package com.example.steam_store_app.core.service

import com.example.steam_store_app.core.service.model.StoreSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchStoreApi {
  @GET("api/storesearch/")
  fun getEntries(
    @Query("term") term: String?,
    @Query("l") language: String?,
    @Query("cc") cc: String?
  ): Call<StoreSearch>

}