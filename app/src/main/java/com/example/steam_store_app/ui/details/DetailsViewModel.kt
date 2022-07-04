package com.example.steam_store_app.ui.details

import android.app.Application
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.steam_store_app.R
import com.example.steam_store_app.core.data.model.AppDetailsEntity
import com.example.steam_store_app.core.repository.AppDetailsRepository
import kotlinx.coroutines.launch

class DetailsViewModel(
  application: Application,
  private val appDetailsRepository: AppDetailsRepository
) : AndroidViewModel(application) {

  companion object {
    const val SELECTED_APP_ID = "selected_app_id"
  }

  val detailsUpdate = MutableLiveData<AppDetailsEntity>()


  fun refresh(id: Int) {
    viewModelScope.launch {
      appDetailsRepository.data(id) {
        if (it != null)
          detailsUpdate.postValue(it)
      }
      appDetailsRepository.refresh(id) {
        if (it != null)
          detailsUpdate.postValue(it)
      }
    }
  }

  fun navigateToDetailsFragment(appId: Int, navcontroller: NavController) {
    val bundle = bundleOf(
      SELECTED_APP_ID to appId
    )
    navcontroller.navigate(R.id.action_fragmentStore_to_fragmentDetails, bundle)
  }
}