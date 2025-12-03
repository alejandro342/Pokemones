package com.api.pokemones.domain.usecase

import com.api.pokemones.domain.model.PokemonDetail
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 03/12/25
 * @modificationDate: 03/12/25
 * @Description:
 */

class GetSpriteUrlsUseCase @Inject constructor() {
    operator fun invoke(pokemon: PokemonDetail): List<String> {
        return listOfNotNull(
            pokemon.sprites.front_default,
            pokemon.sprites.front_shiny,
            pokemon.sprites.front_female,
            pokemon.sprites.front_shiny_female,
            pokemon.sprites.animated_front
        )
            .filter { it.isNotBlank() }
            .distinct()
    }
}