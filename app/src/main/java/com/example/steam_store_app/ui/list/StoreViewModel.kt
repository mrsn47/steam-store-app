package com.example.steam_store_app.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.steam_store_app.core.data.model.StoreEntry
import com.example.steam_store_app.core.repository.SearchStoreRepository
import kotlinx.coroutines.launch

class StoreViewModel(
  application: Application,
  private val searchStoreRepository: SearchStoreRepository
) : AndroidViewModel(application) {

  val entriesUpdate = MutableLiveData<List<StoreEntry>>()
  lateinit var savedName: String

  fun refresh(data: String?) {
    if (!data.isNullOrBlank())
      savedName = data
    if (this::savedName.isInitialized) {
      viewModelScope.launch {
        searchStoreRepository.data {
          if (!it.isNullOrEmpty())
            entriesUpdate.postValue(it)
        }
        searchStoreRepository.refresh(savedName) {
          entriesUpdate.postValue(it)
        }
      }
    }
  }

}