package com.example.steam_store_app.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.steam_store_app.core.data.model.AppDetailsEntity

@Dao
interface AppDetailsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(it: AppDetailsEntity)

  @Query("SELECT * FROM app_details_table WHERE id = :id LIMIT 1")
  fun data(id: Int): AppDetailsEntity?

  @Query("DELETE FROM app_details_table")
  fun nukeTable()

  @Query("DELETE FROM app_details_table WHERE id = :id")
  fun deleteFromTable(id: Int)
}