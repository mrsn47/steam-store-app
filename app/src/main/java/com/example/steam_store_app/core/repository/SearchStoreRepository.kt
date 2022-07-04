package com.example.steam_store_app.core.repository

import android.util.Log
import com.example.steam_store_app.core.data.dao.StoreEntriesDao
import com.example.steam_store_app.core.data.model.StoreEntry
import com.example.steam_store_app.core.service.SearchStoreApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class SearchStoreRepository(
  private val storeEntriesDao: StoreEntriesDao,
  private val searchStoreApi: SearchStoreApi
) {

  suspend fun data(callback: (List<StoreEntry>?) -> Unit) {
    withContext(Dispatchers.IO) {
      val data = storeEntriesDao.data()
      callback(data)
    }
  }

  suspend fun refresh(search: String, callback: (List<StoreEntry>?) -> Unit) {
    withContext(Dispatchers.IO) {
      val response = searchStoreApi.getEntries(search, "english", "MK").awaitResponse()
      if (response.isSuccessful) {
        Log.d("APICALL", "onResponse ${response.body()?.items?.get(0)?.name}")
        val storeEntriesList = arrayListOf<StoreEntry>()
        storeEntriesDao.nukeTable()
        response.body()?.items?.forEach {
          val storeItem = StoreEntry(
            id = it.id,
            name = it.name,
            currency = it.price?.currency,
            initialCurrency = centsToFloat(it.price?.initial),
            finalCurrency = centsToFloat(it.price?.final),
            tiny_image = it.tiny_image,
            windows = it.platforms.windows,
            linux = it.platforms.linux,
            mac = it.platforms.mac
          )
          storeEntriesList.add(storeItem)
        }
        storeEntriesList.forEach {
          storeEntriesDao.insert(it)
        }
        callback(storeEntriesList.sortedBy { it.name })
      } else {

        Log.d("APICALL", "onFailure")
        callback(null)
      }
    }
  }

  fun centsToFloat(data: Int?): Float {
    return data?.toFloat()?.div(100) ?: 0.0f
  }
}