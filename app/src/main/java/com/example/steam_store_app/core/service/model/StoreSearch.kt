package com.example.steam_store_app.core.service.model

import com.google.gson.annotations.SerializedName

data class StoreSearch(

    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Items>
)