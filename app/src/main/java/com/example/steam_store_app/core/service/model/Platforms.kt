package com.example.steam_store_app.core.service.model

import com.google.gson.annotations.SerializedName

data class Platforms(

    @SerializedName("windows") val windows: Boolean,
    @SerializedName("mac") val mac: Boolean,
    @SerializedName("linux") val linux: Boolean
)