package com.api.pokemones.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.api.pokemones.domain.model.PokemonDetail
import com.api.pokemones.domain.usecase.GetFlavorTextDescriptionUseCase
import com.api.pokemones.domain.usecase.GetPokemonColorUseCase
import com.api.pokemones.domain.usecase.GetPokemonDetailUseCase
import com.api.pokemones.domain.usecase.GetPokemonEvolutionsUseCase
import com.api.pokemones.domain.usecase.GetSpriteUrlsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 05/11/25
 * @modificationDate: 05/11/25
 * @Description:
 */
@HiltViewModel
class PokemonViewModelColor @Inject constructor(
    private val getPokemonColorUseCase: GetPokemonColorUseCase,
    private val getPokemonEvolutionsUseCase: GetPokemonEvolutionsUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getFlavorTextDescriptionUseCase: GetFlavorTextDescriptionUseCase,
    private val getSpriteUrls: GetSpriteUrlsUseCase
) : ViewModel() {

    private val _pokemonColors = mutableStateMapOf<Int, Color>()
    val pokemonColors: SnapshotStateMap<Int, Color> = _pokemonColors

    private val _evolutions = MutableStateFlow<List<String>>(emptyList())
    val evolutions: StateFlow<List<String>> = _evolutions

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)

    private val _spriteUrls = MutableStateFlow<List<String>>(emptyList())


    var flavorDescription by mutableStateOf("")
        private set

    var flavorError by mutableStateOf<String?>(null)
        private set


    private val colorMap = mapOf(
        "black" to Color.Black,
        "blue" to Color(0xFF42A5F5),
        "brown" to Color(0xFFA52A2A),
        "gray" to Color(0xFF9E9E9E),
        "green" to Color(0xFF66BB6A),
        "pink" to Color(0xFFFFC0CB),
        "purple" to Color(0xFF800080),
        "red" to Color(0xFFFFA726),
        "white" to Color(0xFFFFFFFF),
        "yellow" to Color(0xFFE0CB2C)
    )

    fun fetchColorForPokemon(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val colorName = getPokemonColorUseCase(pokemonId)
                val mappedColor = colorMap[colorName.lowercase()] ?: Color.Gray
                _pokemonColors[pokemonId] = mappedColor

                val result = getPokemonDetailUseCase(pokemonId)
                _pokemonDetail.value = result
                setPokemonSprites(result)

            } catch (e: Exception) {
                _pokemonColors[pokemonId] = Color.Gray
                print("Error: $e fetch color")
            }
        }
    }

    fun getEvolution(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val evolutionNames = getPokemonEvolutionsUseCase(pokemonId)
                _evolutions.value = evolutionNames
            } catch (e: Exception) {
                _evolutions.value = emptyList()
                print("error List evolutions: $e")
            }
        }
    }

    fun loadFlavorDescription(pokemonId: Int) {
        viewModelScope.launch {
            try {
                flavorDescription = getFlavorTextDescriptionUseCase(pokemonId)
                flavorError = null
            } catch (e: Exception) {
                flavorError = e.message ?: "Error al cargar la descripción"
                flavorDescription = ""
            }
        }
    }

    fun setPokemonSprites(pokemon: PokemonDetail) {
        _spriteUrls.value = getSpriteUrls(pokemon)
    }
}