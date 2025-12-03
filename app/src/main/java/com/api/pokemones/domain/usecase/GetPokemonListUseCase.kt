package com.api.pokemones.domain.usecase

import com.api.pokemones.domain.model.PokemonListItem
import com.api.pokemones.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */
class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(
        limit: Int = 20,
        offset: Int = 0
    ): List<PokemonListItem> {
        return repository.getPokemonList(limit, offset)
    }
}