package com.api.pokemones.presentation.screen.orderelementsdetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.CardTopPokemonDetail
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.EvolutionList
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.PlaySoundButtonWithMediaPlayer
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.PokeballCardO
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.PokeballCardViewPokemon
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.PokemonStats
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.PokemonTypeChip
import com.api.pokemones.presentation.screen.viewcontent.detailview.components.SpriteRow
import com.api.pokemones.presentation.ui.components.TextDetailGrl
import com.api.pokemones.presentation.viewmodel.PokemonDetailViewModel
import com.api.pokemones.presentation.viewmodel.PokemonViewModelColor
import com.api.pokemones.utils.MyTextApp.Companion.DESCRIPTION_POKEMON
import com.api.pokemones.utils.MyTextApp.Companion.EVOLUTIONS_POKEMON
import com.api.pokemones.utils.MyTextApp.Companion.SOUNDS_ACTUALITY_POKEMON
import com.api.pokemones.utils.MyTextApp.Companion.SOUNDS_CLASSIC_POKEMON
import com.api.pokemones.utils.MyTextApp.Companion.SOUNDS_POKEMON
import com.api.pokemones.utils.MyTextApp.Companion.SPRITES_POKEMON
import com.api.pokemones.utils.MyTextApp.Companion.STAS_POKEMON


/**
 * @Author: Alejandro Ambrosio
 * @Date: 05/11/25
 * @modificationDate: 05/11/25
 * @Description: Orden de los elementos de la vista detalle
 */
@Composable
fun OrderElementsDetail(
    pokemonId: Int,
    paddingV: PaddingValues,
    detailViewModel: PokemonDetailViewModel = hiltViewModel(),
    colorVM: PokemonViewModelColor = hiltViewModel()
) {

    val pokemonDetail = detailViewModel.pokemonDetail.collectAsState().value
    val spriteUrls by detailViewModel.spriteUrls.collectAsState()
    val brush = detailViewModel.backgroundBrush.collectAsState().value
    val scrollState = rememberScrollState()
    val bgColor = colorVM.pokemonColors[pokemonId] ?: Color.Gray

    val evolutionList by colorVM.evolutions.collectAsState()


    val description = colorVM.flavorDescription
    val error = colorVM.flavorError

    var showImage by remember { mutableStateOf(false) }

    LaunchedEffect(pokemonId) {
        detailViewModel.fetchPokemonDetail(pokemonId)
        if (!colorVM.pokemonColors.containsKey(pokemonId)) {
            colorVM.fetchColorForPokemon(pokemonId)
        }
        colorVM.getEvolution(pokemonId)
        colorVM.loadFlavorDescription(pokemonId)

        Log.d("VAAT", "$error")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingV)
            .verticalScroll(scrollState)
            .background(brush ?: Brush.verticalGradient(listOf(Color.Gray, Color.DarkGray))),

        ) {
        CardTopPokemonDetail(
            pokemonDetail!!.name,
            pokemonId.toString(),
            urlImg = pokemonDetail.spriteUrl,
            background = bgColor,
            showImage = showImage
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            PokeballCardViewPokemon(onToggle = { opened -> showImage = opened })

        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            TextDetailGrl(SPRITES_POKEMON)
            SpriteRow(spriteUrls = spriteUrls)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            pokemonDetail.types.forEach { type ->
                Spacer(modifier = Modifier.weight(1f))
                PokemonTypeChip(type = type)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextDetailGrl(DESCRIPTION_POKEMON)
            PokeballCardO(modifier = Modifier, description = description)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextDetailGrl(EVOLUTIONS_POKEMON)
            EvolutionList(evolutionNames = evolutionList)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 40.dp)
        ) {
            TextDetailGrl(STAS_POKEMON)
            Spacer(Modifier.weight(1f))
        }
        PokemonStats(stats = pokemonDetail!!.stats)
        TextDetailGrl(SOUNDS_POKEMON)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            PlaySoundButtonWithMediaPlayer(
                url = pokemonDetail.cries.latest,
                label = SOUNDS_ACTUALITY_POKEMON
            )
            PlaySoundButtonWithMediaPlayer(
                url = pokemonDetail.cries.legacy,
                label = SOUNDS_CLASSIC_POKEMON
            )
        }
    }
}
