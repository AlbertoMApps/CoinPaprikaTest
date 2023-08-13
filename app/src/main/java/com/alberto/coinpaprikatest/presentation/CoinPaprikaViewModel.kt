package com.alberto.coinpaprikatest.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.domain.CoinRepositoryService
import com.alberto.coinpaprikatest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPaprikaViewModel @Inject constructor(
    private val coinRepositoryService: CoinRepositoryService
) : ViewModel() {

    private var coinsJob: Job? = null
    private val _coinsListState = mutableStateOf(CoinsPaprikaViewState())
    val coinsListState = _coinsListState

    private var coinJob: Job? = null
    private val _coinState = mutableStateOf(CoinPaprikaViewState())
    val coinState = _coinState

    init {
        getCoins()
    }

    /*
    * getCoins() propagate states for coins based on its repository
     */
    fun getCoins() {
        coinsJob?.cancel()
        coinsJob = viewModelScope.launch {
            coinRepositoryService.getCoins().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _coinsListState.value = CoinsPaprikaViewState(
                            isLoading = true,
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Success -> {
                        _coinsListState.value = CoinsPaprikaViewState(
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Error -> {
                        _coinsListState.value = CoinsPaprikaViewState(
                            errorMessage = result.message ?: "Unexpected error loading coins"
                        )
                    }

                }
            }.launchIn(this)
        }
    }

    /*
    * getCoin() propagate states for a coin based on its id from the database repository
     */

    fun getCoin(id: String) {
        coinsJob?.cancel()
        coinJob?.cancel()
        coinJob = viewModelScope.launch {
            coinRepositoryService.getCoin(id).first { result ->
                when (result) {
                    is Resource.Success -> {
                        _coinState.value = CoinPaprikaViewState(
                            data = result.data ?: Coin()
                        )
                        true
                    }

                    else -> {
                        _coinState.value = CoinPaprikaViewState(
                            errorMessage = "The coin selected could not be found."
                        )
                        true
                    }
                }
            }
        }
    }

}