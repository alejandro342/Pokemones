package com.api.pokemones.domain.usecase

import com.api.pokemones.domain.repository.PokemonRepository

/**
 * @Author: Alejandro Ambrosio
 * @Date: 05/11/25
 * @modificationDate: 05/11/25
 * @Description:
 */

class GetPokemonEvolutionsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int): List<String> {
        return repository.getPokemonEvolutions(pokemonId)
    }
}