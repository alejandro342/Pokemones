package com.api.pokemones.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.api.pokemones.domain.model.PokemonListItem
import com.api.pokemones.domain.repository.PokemonRepository

/**
 * @Author: Alejandro Ambrosio
 * @Date: 02/07/25
 * @Description:
 */
class PokemonPagingSource(
    private val repository: PokemonRepository
) : PagingSource<Int, PokemonListItem>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItem> {
        return try {
            val currentPage = params.key ?: 0
            val pokemonList = repository.getPokemonList(
                limit = params.loadSize,
                offset = currentPage * params.loadSize
            )

            LoadResult.Page(
                data = pokemonList,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (pokemonList.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}