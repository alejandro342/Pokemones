package com.api.pokemones.domain.usecase

import com.api.pokemones.domain.model.PokemonDetail
import com.api.pokemones.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */
class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: Int): PokemonDetail {
        return repository.getPokemonDetail(pokemonId)
    }
}