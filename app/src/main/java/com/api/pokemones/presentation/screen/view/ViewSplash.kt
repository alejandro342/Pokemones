package com.api.pokemones.presentation.screen.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.api.pokemones.presentation.screen.viewcontent.contentsplash.ContentSplashScreen

/**
 * @Author: Alejandro Ambrosio
 * @Date: 03/12/25
 * @modificationDate: 03/12/25
 * @Description: vista spalsh
 */


@Composable
fun ViewSplash(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1000)
        onFinished()
    }
    Scaffold { pad ->

        ContentSplashScreen(pad)
    }
}
