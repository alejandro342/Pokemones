package com.api.pokemones.navhots.routes

sealed class Routes(val route: String) {

    //pokemones
    object ScreenPokemon : Routes("viewPokemon")
    object ScreenDetailPokemon : Routes("pokemon_detail/{pokemonId}") {
        fun createRoute(pokemonId: Int) = "pokemon_detail/$pokemonId"
    }

}