package com.api.pokemones.di


import com.api.pokemones.data.remote.api.PokeApiService
import com.api.pokemones.data.repository.PokemonRepositoryImpl
import com.api.pokemones.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        apiService: PokeApiService
    ): PokemonRepository {
        return PokemonRepositoryImpl(apiService)
    }
}