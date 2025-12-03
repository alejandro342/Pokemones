package com.api.pokemones.domain.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?,
    val animated_front: String?,
    val animated_back: String?
)


data class AnimatedSpritesResponse(
    val front_default: String?,
    val back_default: String?
)

data class BlackWhiteResponse(
    val animated: AnimatedSpritesResponse
)

data class GenerationVResponse(
    @SerializedName("black-white") val blackWhite: BlackWhiteResponse
)

data class VersionsResponse(
    @SerializedName("generation-v") val generationV: GenerationVResponse
)


data class OtherSpritesResponse(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkResponse
)

data class OfficialArtworkResponse(@SerializedName("front_default") val front_default: String)
data class SpritesFullResponse(
    val back_default: String,
    val back_female: String?,
    val back_shiny: String,
    val back_shiny_female: String?,
    val front_default: String,
    val front_female: String?,
    val front_shiny: String,
    val front_shiny_female: String?,
    val other: OtherSpritesResponse,
    val versions: VersionsResponse
)