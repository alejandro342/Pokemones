package com.api.pokemones.data.remote.response

data class EvolutionChainResponse(
    val chain: ChainLink
)

data class ChainLink(
    val species: NamedAPIResource,
    val evolves_to: List<ChainLink>
)

data class NamedAPIResource(
    val name: String,
    val url: String
)
