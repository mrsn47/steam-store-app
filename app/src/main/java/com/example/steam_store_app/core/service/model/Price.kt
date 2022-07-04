package com.example.steam_store_app.core.service.model

import com.google.gson.annotations.SerializedName

data class Price(

    @SerializedName("currency") val currency: String,
    @SerializedName("initial") val initial: Int,
    @SerializedName("final") val final: Int
)