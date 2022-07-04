package com.example.steam_store_app.core.service

import AppIdList
import AppResponse
import com.google.gson.*
import java.lang.reflect.Type

object GsonProvider {

  fun get() : Gson {
    val gson = GsonBuilder().registerTypeAdapter(AppIdList::class.java, object :
      JsonDeserializer<AppIdList> {
      override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
      ): AppIdList {
        val map: HashMap<String, AppResponse> = readServiceUrlMap(json!!.asJsonObject)
        return AppIdList(map)
      }
    }).serializeNulls().create()
    return gson
  }

  @Throws(JsonSyntaxException::class)
  private fun readServiceUrlMap(jsonObject: JsonObject?): HashMap<String, AppResponse> {
    val gson = Gson()
    val products: HashMap<String, AppResponse> = HashMap()
    for (entry in jsonObject!!.entrySet()) {
      val value: AppResponse = gson.fromJson(entry.value, AppResponse::class.java)
      products[entry.key] = value
    }
    return products
  }
}