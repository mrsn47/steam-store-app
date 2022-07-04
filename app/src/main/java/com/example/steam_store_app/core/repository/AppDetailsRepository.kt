package com.example.steam_store_app.core.repository

import android.util.Log
import com.example.steam_store_app.core.data.dao.AppDetailsDao
import com.example.steam_store_app.core.data.model.AppDetailsEntity
import com.example.steam_store_app.core.service.DetailsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class AppDetailsRepository(
  private val appDetailsDao: AppDetailsDao,
  private val detailsApi: DetailsApi
) {

  suspend fun data(id: Int, callback: (AppDetailsEntity?) -> Unit) {
    withContext(Dispatchers.IO) {
      val data = appDetailsDao.data(id)
      callback.invoke(data)
    }
  }

  suspend fun refresh(id: Int, callback: (AppDetailsEntity?) -> Unit) {
    withContext(Dispatchers.IO) {
      val response = detailsApi.getDetails(id).awaitResponse()
      if (response.isSuccessful) {
        var details: AppDetailsEntity? = null
        appDetailsDao.deleteFromTable(id)
        val appResponse = response.body()?.map?.get(id.toString())?.data
        if (appResponse != null) {
          details = AppDetailsEntity(
            id = appResponse.steam_appid,
            name = appResponse.name,
            isFree = appResponse.is_free,
            shortDescription = appResponse.short_description,
            developers = appResponse.developers,
            publishers = appResponse.publishers,
            categories = appResponse.categories.map { it.description },
            genres = appResponse.genres.map { it.description },
            headerImage = appResponse.header_image
          )
          appDetailsDao.insert(details)
          callback(details)
        } else {

          Log.d("APICALL", "onFailure")
          callback(null)
        }
      }
    }
  }
}