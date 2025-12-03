package com.api.pokemones.presentation.screen.viewcontent.detailview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.PokemonImage

/**
 * @Author: Alejandro Ambrosio
 * @Date: 13/11/25
 * @modificationDate: 13/11/25
 * @Description:
 */

@Preview
@Composable
fun RoundedCardPokemonList(
    modifier: Modifier = Modifier,
    url: String = "",
    colorBack: Color = Color.Red
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(color = colorBack.copy(alpha = 0.3f))
    ) {

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            PokemonImage(url, "pokemon", modifier = Modifier.size(60.dp))
        }
    }
}