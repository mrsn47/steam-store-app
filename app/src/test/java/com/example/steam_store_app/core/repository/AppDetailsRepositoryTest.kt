package com.example.steam_store_app.core.repository

import AppIdList
import com.example.steam_store_app.core.data.dao.AppDetailsDao_Impl
import com.example.steam_store_app.core.data.model.AppDetailsEntity
import com.example.steam_store_app.core.service.DetailsApi
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.awaitResponse

class AppDetailsRepositoryTest {

  private val appDetailsDao = mockk<AppDetailsDao_Impl>(relaxed = true)
  private val detailsApi = mockk<DetailsApi>(relaxed = true)
  private val appDetailsRepository = AppDetailsRepository(appDetailsDao, detailsApi)

  @Before
  fun setUp() {
    clearAllMocks()
    Dispatchers.setMain(Dispatchers.Unconfined)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `Retrieves data`() = runBlocking {
    // given
    val appDetails = mockk<AppDetailsEntity>()
    val cb: (AppDetailsEntity?) -> Unit = mockk(relaxed = true)
    val id = mockk<Int>(relaxed = true)
    every { appDetailsDao.data(any()) } returns appDetails
    // when
    appDetailsRepository.data(id, cb)
    // then
    verify(exactly = 1) { appDetailsDao.data(id) }
  }

  @Test
  fun refresh() = runBlocking {

    // given
    val captureSearchString = slot<String>()
    val id = mockk<Int>(relaxed = true)
    val response: Response<AppIdList> = mockk(relaxed = true)
    val appDetails: AppIdList = mockk(relaxed = true)
    coEvery {
      detailsApi.getDetails(id).awaitResponse()
    } returns response

    every {
      response.isSuccessful
    } returns true

    every {
      response.body()
    } returns appDetails

    // when
    val res = appDetailsRepository.refresh(id, mockk(relaxed = true))

    // then
    verify(exactly = 1) { appDetailsDao.nukeTable() }
  }
}