package com.alberto.coinpaprikatest.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.coinpaprikatest.domain.CoinRepositoryService
import com.alberto.coinpaprikatest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinPaprikaViewModel @Inject constructor(
    private val coinRepositoryService: CoinRepositoryService
) : ViewModel() {

    private var getCoinsJob: Job? = null
    private val _state = mutableStateOf(CoinPaprikaViewState())
    val state = _state

    init {
        getCoins()
    }

    /*
    * getCoins() propagate states for coins based on its repository
     */
    fun getCoins() {
        getCoinsJob?.cancel()
        getCoinsJob = viewModelScope.launch {
            coinRepositoryService.getCoins().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = CoinPaprikaViewState(
                            isLoading = true,
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Success -> {
                        _state.value = CoinPaprikaViewState(
                            data = result.data ?: listOf()
                        )
                    }

                    is Resource.Error -> {
                        _state.value = CoinPaprikaViewState(
                            errorMessage = result.message ?: "Unexpected error loading coins"
                        )
                    }

                }
            }.launchIn(this)
        }
    }

}