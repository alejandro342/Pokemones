package com.api.pokemones.data.remote.response

import com.api.pokemones.domain.model.PokemonDto

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */
data class PokemonListResponse(
    val results: List<PokemonDto>
)