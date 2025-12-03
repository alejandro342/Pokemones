package com.api.pokemones.data.remote.api

import com.api.pokemones.data.remote.response.EvolutionChainResponse
import com.api.pokemones.data.remote.response.PokemonDetailResponse
import com.api.pokemones.data.remote.response.PokemonListResponse
import com.api.pokemones.data.remote.response.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */


interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    /* @GET("pokemon/{name}")
     suspend fun getPokemonDetail(
         @Path("name") name: String
     ): PokemonDetailResponse*/

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse

    @GET("pokemon-species/{id}/")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpeciesResponse

    @GET
    suspend fun getEvolutionChain(@Url url: String): EvolutionChainResponse



}