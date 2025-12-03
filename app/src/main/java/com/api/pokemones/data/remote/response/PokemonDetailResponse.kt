package com.api.pokemones.data.remote.response

import com.api.pokemones.domain.model.Cries
import com.api.pokemones.domain.model.PokemonDetail
import com.api.pokemones.domain.model.Sprites
import com.api.pokemones.domain.model.SpritesFullResponse

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val types: List<TypeResponse>,
    val stats: List<StatResponse>,
    val sprites: SpritesFullResponse,
    val cries: Cries
) {
    fun toDomain(): PokemonDetail {
        return PokemonDetail(
            id = id,
            name = name.replaceFirstChar { it.uppercase() },
            types = types.map { it.type.name },
            stats = stats.associate { it.stat.name to it.base_stat },
            spriteUrl = sprites.other.officialArtwork.front_default,
            cries = cries,
            sprites = Sprites(
                back_default = sprites.back_default,
                back_female = sprites.back_female,
                back_shiny = sprites.back_shiny,
                back_shiny_female = sprites.back_shiny_female,
                front_default = sprites.front_default,
                front_female = sprites.front_female,
                front_shiny = sprites.front_shiny,
                front_shiny_female = sprites.front_shiny_female,
                animated_front = sprites.versions.generationV.blackWhite.animated.front_default,
                animated_back = sprites.versions.generationV.blackWhite.animated.back_default
            )
        )
    }
}


data class TypeResponse(val type: TypeNameResponse)
data class TypeNameResponse(val name: String)
data class StatResponse(val stat: StatNameResponse, val base_stat: Int)
data class StatNameResponse(val name: String)
