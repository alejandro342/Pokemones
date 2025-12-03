package com.api.pokemones.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.api.pokemones.data.repository.PokemonPagingSource
import com.api.pokemones.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Alejandro Ambrosio
 * @Date: 30/06/25
 * @Description:
 */


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    init {
        Thread.sleep(1500)
    }
    val pokemonPager = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            initialLoadSize = 20
        )
    ) {
        PokemonPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}

