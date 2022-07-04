package com.example.steam_store_app

import android.app.Application
import com.example.steam_store_app.core.data.SteamAppDatabase
import com.example.steam_store_app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    ApiModule.initialize("https://store.steampowered.com/")
    // Start and load initial Koin modules
    startKoin {
      androidContext(applicationContext)
      loadKoinModules(listOf(ApiModule.koinModule, commonModule, repositoryModule, uiModule))
    }

    SteamAppDatabase.getDatabase(applicationContext)
  }
}