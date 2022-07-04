package com.example.steam_store_app.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.steam_store_app.R
import com.example.steam_store_app.core.data.model.AppDetailsEntity
import com.example.steam_store_app.databinding.FragmentDetailsBinding
import com.example.steam_store_app.ui.common.BaseFragment
import com.example.steam_store_app.ui.details.DetailsViewModel.Companion.SELECTED_APP_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
  override fun provideFragmentBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
  ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

  private val detailsViewModel: DetailsViewModel? by viewModel()
  private var appId: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    appId = arguments?.getInt(SELECTED_APP_ID)
    Log.d("DETAILS_FRAGMENT", "DetailsFragment onCreate with arg app id $appId ")
    detailsViewModel?.detailsUpdate?.observe(this) {
      refreshView(it)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Log.d("DETAILS_FRAGMENT", "DetailsFragment OnCreateView ")
    detailsViewModel?.refresh(appId!!)
    super.onViewCreated(view, savedInstanceState)
  }

  private fun refreshView(appDetailsEntity: AppDetailsEntity) {
    binding?.detailsConstraintLayout?.visibility = View.VISIBLE
    binding?.detailsShortDescription?.text = appDetailsEntity.shortDescription
    var categories = String()
    appDetailsEntity.categories.forEach {
      categories += "$it, "
    }
    var genres = String()
    appDetailsEntity.genres.forEach {
      genres += "$it, "
    }
    var developers = String()
    appDetailsEntity.developers.forEach {
      developers += "$it, "
    }
    var publishers = String()
    appDetailsEntity.publishers.forEach {
      publishers += "$it, "
    }
    binding?.detailsCategories?.text = categories.removeSuffix(", ")
    binding?.detailsGenres?.text = genres.removeSuffix(", ")
    binding?.detailsDevelopers?.text = developers.removeSuffix(", ")
    binding?.detailsPublishers?.text = publishers.removeSuffix(", ")

    binding?.detailsHeaderImage?.let {
      Glide.with(requireContext()).load(appDetailsEntity.headerImage).into(
        it
      )
    }
  }
}