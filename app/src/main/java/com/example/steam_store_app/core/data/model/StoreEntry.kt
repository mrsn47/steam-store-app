package com.example.steam_store_app.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_entry_table")
data class StoreEntry(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val name: String,
  val tiny_image: String,
  var currency: String? = null,
  var initialCurrency: Float,
  var finalCurrency: Float,
  val windows: Boolean,
  val mac: Boolean,
  val linux: Boolean
)
