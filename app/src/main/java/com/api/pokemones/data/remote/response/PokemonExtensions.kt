package com.api.pokemones.data.remote.response

import com.api.pokemones.domain.model.PokemonDto
import com.api.pokemones.domain.model.PokemonListItem

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */
/*fun PokemonDto.toDomain(): PokemonListItem {
    return PokemonListItem(
        name = this.name,
        url = this.url
    )
}*/
fun PokemonDto.toDomain(): PokemonListItem {

    val id = url.trimEnd('/').split("/").last()

    return PokemonListItem(
        name = this.name,
        url = this.url,
        imagePo = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}