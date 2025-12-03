package com.api.pokemones.domain.usecase

import com.api.pokemones.domain.repository.PokemonRepository

/**
 * @Author: Alejandro Ambrosio
 * @Date: 05/11/25
 * @modificationDate: 05/11/25
 * @Description:
 */

class GetFlavorTextDescriptionUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(id: Int): String = repository.getFlavorTextDescription(id)
}


