package com.example.steam_store_app.di

import com.example.steam_store_app.core.data.SteamAppDatabase
import com.example.steam_store_app.core.data.dao.AppDetailsDao
import com.example.steam_store_app.core.data.dao.StoreEntriesDao
import com.example.steam_store_app.core.repository.AppDetailsRepository
import com.example.steam_store_app.core.repository.SearchStoreRepository
import com.example.steam_store_app.core.service.DetailsApi
import com.example.steam_store_app.core.service.GsonProvider
import com.example.steam_store_app.core.service.SearchStoreApi
import com.example.steam_store_app.ui.details.DetailsViewModel
import com.example.steam_store_app.ui.list.StoreViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiModule {
  private var retrofit: Retrofit? = null

  fun initialize(baseUrl: String) {
    val gson = GsonProvider.get()
    val builder = Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create(gson))
    retrofit = builder.build()
  }

  val koinModule = module {
    single { retrofit }
    single { retrofit?.create(SearchStoreApi::class.java) as SearchStoreApi }
    single { retrofit?.create(DetailsApi::class.java) as DetailsApi }
  }

}

val commonModule = module {
  single { SteamAppDatabase.getDatabase(androidContext()).storeEntries() as StoreEntriesDao }
  single { SteamAppDatabase.getDatabase(androidContext()).appDetails() as AppDetailsDao }
  single { Gson() }
}

val repositoryModule = module {
  single { SearchStoreRepository(get(), get()) as SearchStoreRepository }
  single { AppDetailsRepository(get(), get()) as AppDetailsRepository }
}

val uiModule = module {
  viewModel { StoreViewModel(androidApplication(), get()) }
  viewModel { DetailsViewModel(androidApplication(), get()) }
}