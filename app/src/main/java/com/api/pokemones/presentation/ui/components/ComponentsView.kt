package com.api.pokemones.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.api.pokemones.domain.model.PokemonListItem
import com.api.pokemones.presentation.screen.viewcontent.detailview.RoundedCardPokemonList
import com.api.pokemones.presentation.ui.components.colorsstats.getStatColor
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */

@Composable
fun PokemonItem(
    pokemon: PokemonListItem,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    val colorName = if (backgroundColor == Color.White) Color.Black else Color.White

    Box(
        modifier = Modifier
            .padding(top = 30.dp, start = 10.dp, end = 10.dp)
            .clickable(onClick = onClick)
            .wrapContentSize()
    ) {

        Card(
            modifier = Modifier
                .height(150.dp)
                .width(200.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp)
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    color = colorName,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 5.dp, top = 8.dp, bottom = 10.dp)
                )
            }
        }

        PokemonListImage(
            imageUrl = pokemon.imagePo,
            contentDescription = pokemon.name,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterStart)
                .offset(y = (-45).dp)
                .zIndex(3f)
        )

        RoundedCardPokemonList(
            modifier = Modifier.align(Alignment.BottomEnd),
            pokemon.imagePo
        )

    }
}

/*
@Composable
fun PokemonListImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .padding(8.dp)
    )
}*/
@Composable
fun PokemonListImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier.padding(8.dp)
    )
}


@Composable
fun Modifier.shimmerShape(
    shimmerInstance: Shimmer = rememberShimmer(ShimmerBounds.View),
    shape: Shape = RoundedCornerShape(8.dp),
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
): Modifier {
    return this
        .shimmer(shimmerInstance)
        .background(color = color, shape = shape)
        .drawWithContent { }
}


@Composable
fun StatItem(statName: String, value: Int, maxValue: Int = 255) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextStatsName(statName = statName)
            AnimatedProgressBar(
                progress = value.toFloat() / maxValue,
                modifier = Modifier.weight(1f),
                color = getStatColor(statName),
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                height = 12.dp
            )
            TextStatsLevel(value = value)
        }
    }
}



