package com.alberto.coinpaprikatest.presentation.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alberto.coinpaprikatest.presentation.ui.theme.CoinPaprikaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinPaprikaTestTheme {
                CoinPaprikaList()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoinPaprikaListPreview() {
    CoinPaprikaTestTheme {
        CoinPaprikaList()
    }
}