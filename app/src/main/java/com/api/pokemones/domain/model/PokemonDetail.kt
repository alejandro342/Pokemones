package com.api.pokemones.domain.model

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<String>,
    val stats: Map<String, Int>,
    val spriteUrl: String,
    val cries: Cries,
    val sprites: Sprites
)