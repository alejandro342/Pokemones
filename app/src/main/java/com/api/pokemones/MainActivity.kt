package com.api.pokemones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.api.pokemones.navhots.navigation.NavigationC
import com.api.pokemones.presentation.ui.theme.PokemonesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonesTheme {
                NavigationC()
            }
        }
    }
}