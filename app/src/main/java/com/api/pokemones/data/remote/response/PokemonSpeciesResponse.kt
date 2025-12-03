package com.api.pokemones.data.remote.response

import com.api.pokemones.domain.model.ColorInfo
import com.api.pokemones.domain.model.Evolutions
import com.google.gson.annotations.SerializedName

//data class PokemonSpeciesResponse(
//    val color: ColorInfo,
//    val evolution_chain: Evolutions,
//    val flavorText : FlavorTextEntriesModel
//)

data class PokemonSpeciesResponse(
    val color: ColorInfo,
    val evolution_chain: Evolutions,

    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>
)

data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavor_text: String,
    val language: Language
)

data class Language(val name: String)

