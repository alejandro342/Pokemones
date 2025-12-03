package com.api.pokemones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.api.pokemones.presentation.ui.theme.TopBackground

/**
 * Author: Alejandro Ambrosio
 * Date: 02/04/25
 * Description:
 */

@Composable
fun CustomTopBar(
    title: String = "",
    onBackPressed: () -> Unit = {},
    disable: Boolean = false,
    background: Color = TopBackground,
    favorites: Boolean = false,
    goFavorites: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(background)
            .padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (disable) {
            IconButton(
                onClick = onBackPressed, modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 50.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        if (favorites) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "fav",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable { goFavorites() })
        }
    }
}

@Composable
fun CustomTopBarHome(
    title: String,
    background: Color = TopBackground,
    favorites: Boolean = false,
    goFavorites: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(background)
            .padding(top = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = title,
            color = Color.White,
            fontSize = 25.sp,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))

        if (favorites) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "fav",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clickable { goFavorites() })
        }
    }
}
