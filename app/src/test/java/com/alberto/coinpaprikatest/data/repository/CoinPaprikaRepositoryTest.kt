package com.alberto.coinpaprikatest.data.repository

import android.nfc.Tag
import com.alberto.coinpaprikatest.data.getCoin
import com.alberto.coinpaprikatest.data.remote.api.CoinPaprikaApi
import com.alberto.coinpaprikatest.domain.CoinRepositoryService
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class CoinPaprikaRepositoryTest {

    @Mock
    private lateinit var coinPaprikaApi: CoinPaprikaApi
    private lateinit var coinPaprikaRepositoryImplementation: CoinRepositoryService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        coinPaprikaRepositoryImplementation = CoinPaprikaRepositoryImplementation(coinPaprikaApi)
    }

    @Test
    fun `When getCoins is successful, then it should return coins filtered by tag`() = runTest {
        whenever(coinPaprikaApi.getCoins())
            .thenReturn(listOf(getCoin()))
        whenever(coinPaprikaApi.getCoinInfo(getCoin().id))
            .thenReturn(getCoin())

        val result = coinPaprikaRepositoryImplementation.getCoins().toList()
        coinPaprikaApi.getCoinInfo(getCoin().id).apply {
            assertEquals(id, result[1].data?.get(0)?.id ?: "")
            assertEquals(name, result[1].data?.get(0)?.name ?: "")
            assertEquals(symbol, result[1].data?.get(0)?.symbol ?: "")
            assertEquals(logo, result[1].data?.get(0)?.logo ?: "")
            assertEquals(tags, result[1].data?.get(0)?.tags ?: listOf<Tag>())
        }
    }

    @Test
    fun `When getCoins has an http connection error, then it should return an error`() = runTest {
        whenever(coinPaprikaApi.getCoins())
            .thenThrow(
                HttpException(
                    Response.error<Any>(403, ResponseBody.create("plain/text".toMediaType(), ""))
                )
            )

        val result = coinPaprikaRepositoryImplementation.getCoins().toList()
        assertEquals("HTTP 403 Response.error()", result[1].message)
    }

}