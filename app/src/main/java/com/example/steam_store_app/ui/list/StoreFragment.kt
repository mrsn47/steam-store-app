package com.example.steam_store_app.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.steam_store_app.R
import com.example.steam_store_app.core.data.model.StoreEntry
import com.example.steam_store_app.databinding.FragmentStoreBinding
import com.example.steam_store_app.ui.common.BaseFragment
import com.example.steam_store_app.ui.details.DetailsViewModel
import com.example.steam_store_app.ui.list.recyclerview.StoreEntriesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : BaseFragment<FragmentStoreBinding>(R.layout.fragment_store) {
  override fun provideFragmentBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
  ): FragmentStoreBinding = FragmentStoreBinding.inflate(inflater, container, false)

  private val storeViewModel: StoreViewModel? by viewModel()
  private val detailsViewModel: DetailsViewModel? by viewModel()

  private lateinit var storeEntriesAdapter: StoreEntriesAdapter
  private var searchEditText: EditText? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    storeViewModel?.entriesUpdate?.observe(this) {
      setUpAdapters(it)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    searchEditText = binding?.storeSearchEditText
    binding?.searchImageView?.setOnClickListener {
      requireContext().hideKeyboard()
      val name = searchEditText?.text?.toString()
      storeViewModel?.refresh(name)
    }
    storeViewModel?.refresh(null)
  }

  private fun setUpAdapters(items: List<StoreEntry>) {
    val layoutManager = LinearLayoutManager(this.requireContext())
    val recyclerView = binding?.storeRecyclerView
    storeEntriesAdapter =
      StoreEntriesAdapter(this.requireContext(), items) { itemId ->
        // navigate to details with item id
        Log.d("LIST_FRAGMENT", "Clicked item with id $itemId")
        detailsViewModel?.navigateToDetailsFragment(itemId, findNavController())
      }
    recyclerView?.adapter = storeEntriesAdapter
    recyclerView?.layoutManager = layoutManager
  }
}