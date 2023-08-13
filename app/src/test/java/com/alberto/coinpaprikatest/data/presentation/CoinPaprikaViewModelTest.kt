package com.alberto.coinpaprikatest.data.presentation

import com.alberto.coinpaprikatest.data.getCoin
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.domain.CoinRepositoryService
import com.alberto.coinpaprikatest.presentation.CoinPaprikaViewModel
import com.alberto.coinpaprikatest.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinPaprikaViewModelTest {

    @RelaxedMockK
    private lateinit var coinRepositoryService: CoinRepositoryService
    private lateinit var viewModel: CoinPaprikaViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the repository service is loading a list of coins, then the view model should be loading`() =
        runTest {
            val flow = flow {
                emit(Resource.Loading(data = emptyList<Coin>()))
            }
            every { coinRepositoryService.getCoins() } returns flow
            viewModel = CoinPaprikaViewModel(coinRepositoryService)
            val state = viewModel.coinsListState.value
            assertEquals(true, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(emptyList<Coin>(), state.data)
        }

    @Test
    fun `When the repository service has a list of coins, then the view model should return them`() =
        runTest {
            val flow = flow {
                emit(Resource.Success(data = listOf(getCoin())))
            }
            every { coinRepositoryService.getCoins() } returns flow
            viewModel = CoinPaprikaViewModel(coinRepositoryService)
            val state = viewModel.coinsListState.value
            assertEquals(false, state.isLoading)
            assertEquals("", state.errorMessage)
            assertEquals(listOf(getCoin()), state.data)
        }

    @Test
    fun `When the repository service has an error after loading a list of coins, then the view model should return an unexpected error`() =
        runTest {
            val flow = flow {
                emit(
                    Resource.Error(
                        message = "Unexpected error loading coins",
                        data = emptyList<Coin>()
                    )
                )
            }
            every { coinRepositoryService.getCoins() } returns flow
            viewModel = CoinPaprikaViewModel(coinRepositoryService)
            val state = viewModel.coinsListState.value
            assertEquals(false, state.isLoading)
            assertEquals("Unexpected error loading coins", state.errorMessage)
            assertEquals(emptyList<Coin>(), state.data)
        }

    @Test
    fun `When the repository service gets a coin, then the view model should return a coin selected by id`() =
        runTest {
            val flow = flow {
                emit(Resource.Success(data = Coin()))
            }
            every { coinRepositoryService.getCoin("btc-bitcoin") } returns flow
            viewModel = CoinPaprikaViewModel(coinRepositoryService)
            val state = viewModel.coinState.value
            assertEquals("", state.errorMessage)
            assertEquals(Coin(), state.data)
        }

}