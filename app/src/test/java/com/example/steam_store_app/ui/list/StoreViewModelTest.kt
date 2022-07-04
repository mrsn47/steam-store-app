package com.example.steam_store_app.ui.list

import android.app.Application
import com.example.steam_store_app.core.repository.SearchStoreRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class StoreViewModelTest {
  private val searchStoreRepository: SearchStoreRepository = mockk(relaxed = true)
  private val application: Application = mockk(relaxed = true)
  private val storeViewModel = StoreViewModel(application, searchStoreRepository)

  @Before
  fun setup() {
    Dispatchers.setMain(Dispatchers.Unconfined)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun refresh() {
    // given
    val search = "mock"

    // when
    storeViewModel.refresh(search)

    // then
    coVerify(exactly = 1) { searchStoreRepository.data(any()) }
    coVerify(exactly = 1) { searchStoreRepository.refresh(search, any()) }
  }

}