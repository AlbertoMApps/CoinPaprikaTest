package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.data.remote.model.Tag
import com.alberto.coinpaprikatest.presentation.ui.theme.CoinPaprikaTestTheme

@Composable
fun CoinPaprikaListItem(
    coin: Coin,
    modifier: Modifier = Modifier
) {
    Card(
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        CardContent(coin)
    }

}

@Composable
private fun CardContent(coin: Coin) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = coin.logo),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )
            Text(
                text = coin.name.plus("(").plus(coin.symbol).plus(")") ?: "",
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinPaprikaListItemPreview() {
    CoinPaprikaTestTheme {
        CoinPaprikaListItem(
            Coin(
                id = "btc-bitcoin",
                name = "Bitcoin",
                symbol = "BTC",
                logo = "https://static.coinpaprika.com/coin/bnb-binance-coin/logo.png",
                tags = listOf(
                    Tag(
                        id = "blockchain-service",
                        name = "Blockchain Service"
                    )
                )
            )
        )
    }
}