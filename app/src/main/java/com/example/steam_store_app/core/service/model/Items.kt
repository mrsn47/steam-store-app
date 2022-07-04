package com.example.steam_store_app.core.service.model

import com.google.gson.annotations.SerializedName

data class Items(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Price?,
    @SerializedName("tiny_image") val tiny_image: String,
    @SerializedName("metascore") val metascore: String,
    @SerializedName("platforms") val platforms: Platforms,
    @SerializedName("streamingvideo") val streamingvideo: Boolean
)