package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.alberto.coinpaprikatest.R
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.presentation.CoinPaprikaViewModel
import com.alberto.coinpaprikatest.presentation.ui.theme.CoinPaprikaTestTheme
import com.alberto.coinpaprikatest.utils.getCoin


@Composable
fun CoinPaprikaDetailedItem(
    viewModel: CoinPaprikaViewModel = hiltViewModel()
) {
    val coin = viewModel.state.value.data[0]
    CoinPaprikaDetailedItemScreen(coin)
}

@Composable
fun CoinPaprikaDetailedItemScreen(coin: Coin) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = coin.logo,
            contentDescription = null,
            error = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .size(300.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = coin.name.plus("(").plus(coin.symbol).plus(")"),
            style = MaterialTheme.typography.h3.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = coin.description ?: "",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(all = 24.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(vertical = 4.dp)
        ) {
            coin.tags?.let { tags ->
                items(items = tags) { tag ->
                    Text(
                        text = "#".plus(tag.name),
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinPaprikaDetailedItemScreenPreview() {
    CoinPaprikaTestTheme {
        CoinPaprikaDetailedItemScreen(getCoin())
    }
}