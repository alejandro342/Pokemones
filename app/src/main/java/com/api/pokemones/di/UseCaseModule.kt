package com.api.pokemones.di


import com.api.pokemones.domain.repository.PokemonRepository
import com.api.pokemones.domain.usecase.GetFlavorTextDescriptionUseCase
import com.api.pokemones.domain.usecase.GetPokemonColorUseCase
import com.api.pokemones.domain.usecase.GetPokemonDetailUseCase
import com.api.pokemones.domain.usecase.GetPokemonEvolutionsUseCase
import com.api.pokemones.domain.usecase.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        repository: PokemonRepository
    ): GetPokemonListUseCase {
        return GetPokemonListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(
        repository: PokemonRepository
    ): GetPokemonDetailUseCase {
        return GetPokemonDetailUseCase(repository)
    }

    @Provides
    fun provideGetPokemonColorUseCase(
        repository: PokemonRepository
    ): GetPokemonColorUseCase {
        return GetPokemonColorUseCase(repository)
    }

    @Provides
    fun provideGetPokemonEvolutionsUseCase(repository: PokemonRepository): GetPokemonEvolutionsUseCase {
        return GetPokemonEvolutionsUseCase(repository)
    }

    @Provides
    fun providesFlavorTextDescriptionUseCase(repository: PokemonRepository): GetFlavorTextDescriptionUseCase {
        return GetFlavorTextDescriptionUseCase(repository)
    }
}
/*
@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPokemonColorUseCase(
        repository: PokemonRepository
    ): GetPokemonColorUseCase {
        return GetPokemonColorUseCase(repository)
    }
}*/