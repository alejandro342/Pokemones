package com.api.pokemones.presentation.screen.viewcontent.detailview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.api.pokemones.presentation.screen.orderelementsdetail.OrderElementsDetail
import com.api.pokemones.presentation.viewmodel.PokemonDetailViewModel

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */

@SuppressLint("SuspiciousIndentation")
@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    padding: PaddingValues,
    detailViewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonDetail = detailViewModel.pokemonDetail.collectAsState().value
    LaunchedEffect(pokemonId) {
        detailViewModel.fetchPokemonDetail(pokemonId)
    }

    when {
        pokemonDetail == null -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            Column(modifier = Modifier.fillMaxSize()) {
                OrderElementsDetail(pokemonId = pokemonId, padding)
            }
        }
    }
}
