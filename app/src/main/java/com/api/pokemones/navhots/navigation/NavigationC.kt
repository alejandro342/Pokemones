package com.api.pokemones.navhots.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.api.pokemones.navhots.routes.Routes
import com.api.pokemones.presentation.screen.view.ViewDetailPokemon
import com.api.pokemones.presentation.screen.view.ViewPokemon

/**
 * Author: Alejandro Ambrosio
 * Date: 02/04/25
 * Description:
 */

@Composable
fun NavigationC() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ScreenPokemon.route) {

        composable(route = Routes.ScreenPokemon.route) { ViewPokemon(navController = navController) }
        composable(route = Routes.ScreenPokemon.route) {
            ViewPokemon(navController = navController)
        }
        composable(
            route = Routes.ScreenDetailPokemon.route,
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 0
            ViewDetailPokemon(pokemonId = pokemonId, navController = navController)
        }
    }
}