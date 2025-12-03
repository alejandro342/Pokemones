package com.api.pokemones.presentation.viewmodel

import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.api.pokemones.domain.model.PokemonDetail
import com.api.pokemones.domain.usecase.GetPokemonDetailUseCase
import com.api.pokemones.domain.usecase.GetSpriteUrlsUseCase
import com.api.pokemones.presentation.ui.components.getMetallicBrushForType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description: ViewModel de la vista detalles de pokemon
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getSpriteUrls: GetSpriteUrlsUseCase
) : ViewModel() {

    private val _backgroundBrush = MutableStateFlow<Brush?>(null)
    val backgroundBrush: StateFlow<Brush?> = _backgroundBrush.asStateFlow()

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail.asStateFlow()

    private val _spriteUrls = MutableStateFlow<List<String>>(emptyList())
    val spriteUrls: StateFlow<List<String>> = _spriteUrls

    fun fetchPokemonDetail(pokemonId: Int) {
        viewModelScope.launch {
            val detail = getPokemonDetailUseCase(pokemonId)
            _pokemonDetail.value = detail
            val mainType = detail.types.firstOrNull()
            _backgroundBrush.value = mainType?.let { getMetallicBrushForType(it) }

            val result = getPokemonDetailUseCase(pokemonId)
            _pokemonDetail.value = result
            setPokemonSprites(result)

        }
    }

    fun setPokemonSprites(pokemon: PokemonDetail) {
        _spriteUrls.value = getSpriteUrls(pokemon)
    }
}