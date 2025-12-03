package com.api.pokemones.presentation.screen.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.api.pokemones.components.CustomTopBar
import com.api.pokemones.presentation.screen.viewcontent.detailview.PokemonDetailScreen
import com.api.pokemones.presentation.viewmodel.PokemonViewModelColor

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */

@Composable
fun ViewDetailPokemon(
    pokemonId: Int,
    navController: NavController,
    colorVM: PokemonViewModelColor = hiltViewModel()
) {
    LaunchedEffect(pokemonId) {
        if (!colorVM.pokemonColors.containsKey(pokemonId)) {
            colorVM.fetchColorForPokemon(pokemonId)
        }
    }
    val bgColor = colorVM.pokemonColors[pokemonId] ?: Color.Gray

    Scaffold(topBar = {
        CustomTopBar("Pokedex", onBackPressed = {
            navController.popBackStack()
        }, disable = true, background = bgColor)
    }) { pad ->
        PokemonDetailScreen(pokemonId = pokemonId, pad)
    }
}