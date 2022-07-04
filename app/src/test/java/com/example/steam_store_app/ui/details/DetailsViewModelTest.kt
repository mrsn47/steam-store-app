package com.example.steam_store_app.ui.details

import android.app.Application
import androidx.navigation.NavController
import com.example.steam_store_app.R
import com.example.steam_store_app.core.repository.AppDetailsRepository
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class DetailsViewModelTest {
  private val appDetailsRepository: AppDetailsRepository = mockk(relaxed = true)
  private val application: Application = mockk(relaxed = true)
  val detailsViewModel = DetailsViewModel(application, appDetailsRepository)

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
    val id = Random().nextInt()

    // when
    detailsViewModel.refresh(id)

    // then
    coVerify(exactly = 1) { appDetailsRepository.data(id, any()) }
    coVerify(exactly = 1) { appDetailsRepository.refresh(id, any()) }
  }

  @Test
  fun navigateToDetailsFragment() {
    // given
    val navController: NavController = mockk(relaxed = true)

    // when
    detailsViewModel.navigateToDetailsFragment(mockk(relaxed = true), navController)

    // then
    verify(exactly = 1) {
      navController.navigate(
        R.id.action_fragmentStore_to_fragmentDetails,
        any()
      )
    }
  }
}