package com.api.pokemones.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */

@Composable
fun TextDetailGrl(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(16.dp),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TextStatsName(statName: String) {
    Text(
        text = statName,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.width(100.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TextStatsLevel(value: Int) {
    Text(
        text = value.toString(),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(start = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun AnimatedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color,
    trackColor: Color,
    height: Dp = 1.dp,
    animationDuration: Int = 2000
) {
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(durationMillis = animationDuration)
        )
    }

    Box(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(4.dp))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress.value)
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp))
                .background(color)
        )
    }
}
