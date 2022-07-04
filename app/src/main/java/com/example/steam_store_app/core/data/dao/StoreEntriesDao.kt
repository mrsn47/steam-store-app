package com.example.steam_store_app.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.steam_store_app.core.data.model.StoreEntry


@Dao
interface StoreEntriesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(it: StoreEntry)

  @Query("SELECT * from store_entry_table ORDER BY name ASC")
  fun data(): List<StoreEntry>

  @Query("DELETE FROM store_entry_table")
  fun nukeTable()
}