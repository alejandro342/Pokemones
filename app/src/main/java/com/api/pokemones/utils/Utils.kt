package com.api.pokemones.utils

/**
 * @Author: Alejandro Ambrosio
 * @Date: 08/07/25
 * @Description:
 */
class Utils {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val BASE_URL_IMAGE = "https://img.pokemondb.net/artwork/large/"

        const val FAVORITES_KEY = "favorite_pokemons"

        const val POKEMON_PREFS = "pokemon_prefs"

    }
}

fun String.extractId(): String = split("/").dropLast(1).last()

class MyTextApp {

    companion object {

        //TEXT POKEMONES
        const val POKEMONES_TOP = "Pokemones"
        const val TRY_POKE = "Reintentar"
        const val TEXT_TOP_FAV = "Favoritos"
        const val NOT_POKEMONS_FAVORITE = "No hay Pokémon favoritos"

        //View Details
        const val STAS_POKEMON = "Estadísticas"
        const val EVOLUTIONS_POKEMON = "Evoluciones"
        const val SPRITES_POKEMON = " Sprites"
        const val SOUNDS_POKEMON = " Sonidos"
        const val DESCRIPTION_POKEMON = "Descripción"
        const val SOUNDS_ACTUALITY_POKEMON = " Sonido Actual"
        const val SOUNDS_CLASSIC_POKEMON = " Sonido Clásico"
        const val DESCRIPTION_OF_POKEMON = "Descripción del Pokémon"

        //TEXT GRL
        const val LOADING = "Cargando..."
        const val CLOSE = "Cerrar"
        const val ACCEPT = "Aceptar"

    }
}
