package com.example.steam_store_app.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.steam_store_app.core.data.dao.AppDetailsDao
import com.example.steam_store_app.core.data.dao.StoreEntriesDao
import com.example.steam_store_app.core.data.model.AppDetailsEntity
import com.example.steam_store_app.core.data.model.StoreEntry


@Database(
  entities = [StoreEntry::class, AppDetailsEntity::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SteamAppDatabase : RoomDatabase() {

  abstract fun storeEntries(): StoreEntriesDao
  abstract fun appDetails(): AppDetailsDao

  companion object {
    @Volatile
    private var INSTANCE: SteamAppDatabase? = null

    fun getDatabase(context: Context): SteamAppDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.inMemoryDatabaseBuilder(
          context.applicationContext,
          SteamAppDatabase::class.java
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }
}