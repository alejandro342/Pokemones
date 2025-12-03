package com.api.pokemones.presentation.screen.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.api.pokemones.components.CustomTopBarHome
import com.api.pokemones.presentation.ui.theme.TopBackground
import com.api.pokemones.utils.MyTextApp.Companion.POKEMONES_TOP
import com.api.pokemones.presentation.screen.viewcontent.PokemonListScreen
import com.api.pokemones.presentation.viewmodel.PokemonViewModel

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */

@Composable
fun ViewPokemon(
    viewModelPoke: PokemonViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(topBar = {
        CustomTopBarHome(POKEMONES_TOP, background = TopBackground)
    }) { pad ->
        PokemonListScreen(
            viewModelPoke,
            pad,
            navController = navController
        )
    }
}