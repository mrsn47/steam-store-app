package com.example.steam_store_app.core.repository

import com.example.steam_store_app.core.data.dao.StoreEntriesDao_Impl
import com.example.steam_store_app.core.data.model.StoreEntry
import com.example.steam_store_app.core.service.SearchStoreApi
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchStoreRepositoryTest {

  private val storeEntriesDao = mockk<StoreEntriesDao_Impl>(relaxed = true)
  private val searchStoreApi = mockk<SearchStoreApi>(relaxed = true)
  private val searchStoreRepository = SearchStoreRepository(storeEntriesDao, searchStoreApi)

  @Before
  fun setUp() {
    clearAllMocks()
  }

  @Test
  fun `Retrieves data`() = runBlocking {
    // given
    val storeEntries = mockk<List<StoreEntry>>()
    val cb: (List<StoreEntry>?) -> Unit = mockk(relaxed = true)
    every { storeEntriesDao.data() } returns storeEntries
    // when
    searchStoreRepository.data(cb)
    // then
    coVerify(exactly = 1) { storeEntriesDao.data() }
  }

//  @Test
//  fun refresh() = runBlocking {
//
//    // given
//    val captureSearchString = slot<String>()
//    val mockedString = "Mock"
//    val response: Response<StoreSearch> = mockk(relaxed = true)
//    val mockedStoreSearch: StoreSearch = mockk(relaxed = true)
//    coEvery {
//      searchStoreApi.getEntries(
//        any(),
//        any(),
//        any()
//      ).awaitResponse()
//    } returns response
//
//    every {
//      response.isSuccessful
//    } returns true
//
//    every {
//      response.body()
//    } returns mockedStoreSearch
//
//    // when
//    val res = searchStoreRepository.refresh(mockedString, mockk(relaxed = true))
//
//    // then
//    verify(exactly = 1) { storeEntriesDao.nukeTable() }
//  }

  @Test
  fun `centsToFloat when data is not null`() {
    // when
    val res = searchStoreRepository.centsToFloat(10)
    // then
    assert(res.equals(0.1f))
  }

  @Test
  fun `centsToFloat when data is null`() {
    // when
    val res = searchStoreRepository.centsToFloat(null)
    // then
    assert(res.equals(0.0f))
  }
}