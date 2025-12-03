package com.api.pokemones.presentation.screen.viewcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.api.pokemones.navhots.routes.Routes
import com.api.pokemones.presentation.ui.components.PokemonItem
import com.api.pokemones.presentation.viewmodel.PokemonViewModel
import com.api.pokemones.presentation.viewmodel.PokemonViewModelColor
import com.api.pokemones.utils.MyTextApp.Companion.TRY_POKE
import com.api.pokemones.utils.extractId

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = hiltViewModel(),
    padding: PaddingValues,
    navController: NavController,
    colorVM: PokemonViewModelColor = hiltViewModel()
) {
    val pokemonList = viewModel.pokemonPager.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(padding)
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(pokemonList.itemCount) { index ->
            pokemonList[index]?.let { pokemon ->

                val pokemonId = pokemon.url.extractId().toInt()
                LaunchedEffect(pokemonId) {
                    if (!colorVM.pokemonColors.containsKey(pokemonId)) {
                        colorVM.fetchColorForPokemon(pokemonId)
                    }
                }
                val bgColor = colorVM.pokemonColors[pokemonId] ?: Color.Gray

                PokemonItem(
                    pokemon = pokemon,
                    backgroundColor = bgColor,
                    onClick = {
                        navController.navigate(
                            Routes.ScreenDetailPokemon.createRoute(pokemon.url.extractId().toInt())
                        )
                    }
                )
            }
        }


        if (pokemonList.loadState.append == LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }


        if (pokemonList.loadState.append is LoadState.Error) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = { pokemonList.retry() }) {
                        Text(text = TRY_POKE)
                    }
                }
            }
        }
    }
}