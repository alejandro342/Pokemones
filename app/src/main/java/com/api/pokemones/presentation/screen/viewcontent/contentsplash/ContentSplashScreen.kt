package com.api.pokemones.presentation.screen.viewcontent.contentsplash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * @Author: Alejandro Ambrosio
 * @Date: 03/12/25
 * @modificationDate: 03/12/25
 * @Description:
 */
@Composable
fun ContentSplashScreen(paddingValues: PaddingValues) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(color = Color(0xFF0D47A1))
    ) {
        Text(
            text = "Pokédex",
            style = typography.headlineLarge,
            color = Color.White
        )

        Image(
            painter = painterResource(com.api.pokemones.R.drawable.poke),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
    }
}