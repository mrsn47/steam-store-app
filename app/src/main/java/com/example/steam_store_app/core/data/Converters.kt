package com.example.steam_store_app.core.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import kotlin.reflect.KClass

class Converters {


  @TypeConverter
  fun listOfStringFromString(value: String): List<String> {
    return ObjectSerialization.deserialize(value, SerializedStrings::class).data
  }

  @TypeConverter
  fun stringFromlistOfString(value: List<String>): String {
    val serializedStrings = SerializedStrings(value)
    return ObjectSerialization.serialize(serializedStrings)
  }
}


object ObjectSerialization {
  private val gson = Gson()
  fun serialize(data: Any?): String {
    return gson.toJson(data)
  }

  fun <T : Any> deserialize(data: String, clazz: KClass<T>): T {
    return gson.fromJson(data, clazz.java)
  }
}


data class SerializedStrings(
  val data: List<String>
)