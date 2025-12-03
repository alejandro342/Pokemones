package com.api.pokemones.domain.repository

import com.api.pokemones.domain.model.PokemonDetail
import com.api.pokemones.domain.model.PokemonListItem

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonListItem>
    suspend fun getPokemonDetail(pokemonId: Int): PokemonDetail
    suspend fun getPokemonColor(pokemonId: Int): String
    suspend fun getPokemonEvolutions(pokemonId: Int): List<String>
    suspend fun getFlavorTextDescription(pokemonId: Int): String
}
