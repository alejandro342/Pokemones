package com.api.pokemones.data.repository

import com.api.pokemones.data.remote.api.PokeApiService
import com.api.pokemones.data.remote.response.ChainLink
import com.api.pokemones.data.remote.response.PokemonSpeciesResponse
import com.api.pokemones.data.remote.response.toDomain
import com.api.pokemones.domain.model.Evolutions
import com.api.pokemones.domain.model.PokemonDetail
import com.api.pokemones.domain.model.PokemonListItem
import com.api.pokemones.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonListItem> {
        return try {
            api.getPokemonList(limit, offset).results.map { it.toDomain() }
        } catch (_: Exception) {
            emptyList()
        }
    }

    override suspend fun getPokemonDetail(pokemonId: Int): PokemonDetail {
        val response = api.getPokemonDetail(pokemonId)
        return response.toDomain()
    }

    override suspend fun getPokemonColor(pokemonId: Int): String {
        val response = api.getPokemonSpecies(pokemonId)
        return response.color.name

    }

    override suspend fun getPokemonEvolutions(pokemonId: Int): List<String> {
        val speciesResponse = api.getPokemonSpecies(pokemonId)
        val evolutionUrl = speciesResponse.evolution_chain.url
        val evolutionChain = api.getEvolutionChain(evolutionUrl)

        return extractEvolutionNames(evolutionChain.chain)
    }


    override suspend fun getFlavorTextDescription(pokemonId: Int): String {
        val species = api.getPokemonSpecies(pokemonId)

        val spanish = species.flavorTextEntries
            .firstOrNull { it.language.name.equals("es", ignoreCase = true) }
            ?.flavor_text
            ?.let(::cleanFlavorText)

        val english = species.flavorTextEntries
            .firstOrNull { it.language.name.equals("en", ignoreCase = true) }
            ?.flavor_text
            ?.let(::cleanFlavorText)

        return spanish ?: english ?: "Descripción no disponible"
    }

    private fun cleanFlavorText(text: String): String =
        text.replace('\n', ' ')
            .replace('\u000c', ' ')
            .replace(Regex("\\s+"), " ")



    private fun extractEvolutionNames(chain: ChainLink): List<String> {
        val names = mutableListOf<String>()
        names.add(chain.species.name)
        chain.evolves_to.forEach {
            names.addAll(extractEvolutionNames(it))
        }
        return names
    }

}