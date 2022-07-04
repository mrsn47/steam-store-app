package com.example.steam_store_app.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_details_table")
data class AppDetailsEntity(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val name: String,
  val isFree: Boolean,
  val shortDescription: String,
  var developers: List<String>,
  var publishers: List<String>,
  var categories: List<String>,
  val genres: List<String>,
  var headerImage: String
)