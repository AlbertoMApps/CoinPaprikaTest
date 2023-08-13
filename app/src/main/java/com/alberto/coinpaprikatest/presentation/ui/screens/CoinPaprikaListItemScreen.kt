package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alberto.coinpaprikatest.R
import com.alberto.coinpaprikatest.data.remote.model.Coin

@Composable
fun CoinPaprikaListItemScreen(
    coin: Coin,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
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
                .weight(1f)
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = coin.logo,
                contentDescription = null,
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = coin.name.plus("(").plus(coin.symbol).plus(")"),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider()
        }
    }
}