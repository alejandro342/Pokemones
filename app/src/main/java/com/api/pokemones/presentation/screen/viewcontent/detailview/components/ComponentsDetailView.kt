package com.api.pokemones.presentation.screen.viewcontent.detailview.components

import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.api.pokemones.presentation.ui.components.StatItem
import com.api.pokemones.utils.MyTextApp.Companion.CLOSE
import com.api.pokemones.utils.MyTextApp.Companion.DESCRIPTION_OF_POKEMON
import com.api.pokemones.utils.Utils.Companion.BASE_URL_IMAGE


/**
 * @Author: Alejandro Ambrosio
 * @Date: 06/11/25
 * @modificationDate: 06/11/25
 * @Description: Componentes de la vista detalle
 */
@Composable
fun CardTopPokemonDetail(
    namePokemon: String,
    idPokemon: String,
    urlImg: String,
    background: Color,
    showImage: Boolean
) {

    val alpha by animateFloatAsState(
        targetValue = if (showImage) 1f else 0f,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .graphicsLayer { clip = false }) {
        Card(
            shape = BottomArcShape(90f),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            colors = CardDefaults.cardColors(containerColor = background),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                NamePokemon(namePokemon = namePokemon)
                Spacer(modifier = Modifier.weight(1f))
                IdPokemon(namePokemon = idPokemon)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 90.dp)
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.TopCenter
        ) {
            PokemonImage(
                imageUrl = urlImg, contentDescription = null,

                modifier = Modifier.graphicsLayer {
                    this.alpha = alpha
                }
            )

            AnimatedInvertedTriangleShape(
                isVisible = showImage,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

        }
    }
}

@Composable
fun NamePokemon(namePokemon: String, modifier: Modifier = Modifier) {
    Text(
        text = namePokemon,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun IdPokemon(namePokemon: String, modifier: Modifier = Modifier) {
    Text(
        text = "# $namePokemon",
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}


@Composable
fun PokemonImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()

    AsyncImage(
        model = ImageRequest.Builder(context).data(imageUrl).crossfade(true).build(),
        imageLoader = imageLoader,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}


@Composable
fun PokeballCard(
    modifier: Modifier = Modifier, url: String
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(4.dp, Color.Black, CircleShape)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawArc(
                color = Color.Red, startAngle = 180f, sweepAngle = 180f, useCenter = true
            )

            drawLine(
                color = Color.Black,
                start = Offset(0f, canvasHeight / 2),
                end = Offset(canvasWidth, canvasHeight / 2),
                strokeWidth = 8f
            )

            drawCircle(
                color = Color.Black,
                radius = canvasWidth / 6,
                center = Offset(canvasWidth / 2, canvasHeight / 2)
            )
            drawCircle(
                color = Color.White,
                radius = canvasWidth / 9,
                center = Offset(canvasWidth / 2, canvasHeight / 2)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            PokemonImage(url, "pokemon", modifier = Modifier.size(60.dp))
        }
    }
}

@Composable
fun SpriteRow(spriteUrls: List<String>) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()

        ) {
            items(spriteUrls) { url ->
                Spacer(modifier = Modifier.padding(20.dp))
                PokeballCard(url = url)
            }
        }
    }
}

@Composable
fun EvolutionList(evolutionNames: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(evolutionNames) { name ->
            EvolutionCard(name)
        }
    }
}

@Composable
fun EvolutionCard(name: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonImage(
                imageUrl = "$BASE_URL_IMAGE${name.lowercase()}.jpg",
                contentDescription = name,
                modifier = Modifier
                    .height(90.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PokemonTypeChip(type: String) {
    val (emoji, color) = when (type.lowercase()) {
        "fire" -> "🔥" to Color(0xFFFFA726)
        "water" -> "💧" to Color(0xFF42A5F5)
        "electric" -> "⚡" to Color(0xFFFFEE58)
        "grass" -> "🌿" to Color(0xFF66BB6A)
        "poison" -> "☠️" to Color(0xFFAB47BC)
        "flying" -> "🕊️" to Color(0xFFB39DDB)
        "bug" -> "🐛" to Color(0xFFC0CA33)
        else -> "❓" to Color(0xFF9E9E9E)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .width(120.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            emoji,
            modifier = Modifier.padding(end = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = type.replaceFirstChar { it.uppercase() },
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PokemonStats(stats: Map<String, Int>) {
    val statLabels = mapOf(
        "hp" to "HP",
        "attack" to "Attack",
        "defense" to "Defense",
        "special-attack" to "Atq. Esp",
        "special-defense" to "Def. Esp",
        "speed" to "Speed"
    )

    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            statLabels.forEach { (key, label) ->
                stats[key]?.let { value ->
                    StatItem(
                        statName = label, value = value, maxValue = 255
                    )
                }
            }
        }
    }
}

@Composable
fun PlaySoundButtonWithMediaPlayer(url: String, label: String) {
    var isPlaying by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer() }

    DisposableEffect(Unit) {
        onDispose {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.release()
        }
    }

    Button(onClick = {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                isPlaying = false
            }

            mediaPlayer.setDataSource(url)
            mediaPlayer.setOnPreparedListener {
                it.start()
                isPlaying = true
            }
            mediaPlayer.setOnCompletionListener {
                isPlaying = false
                mediaPlayer.reset()
            }
            mediaPlayer.prepareAsync()
        } catch (e: Exception) {
            Log.e("PlaySound", "Error al reproducir sonido: ${e.message}")
        }
    }) {
        Icon(
            imageVector = if (isPlaying) Icons.AutoMirrored.Filled.VolumeOff else Icons.AutoMirrored.Filled.VolumeUp,
            contentDescription = "Reproducir sonido",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label)
    }
}


@Composable
fun PokeballCardO(
    modifier: Modifier = Modifier,
    description: String
) {
    var isOpen by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var colorCircleCenter = Color.White

    val sweepAngle by animateFloatAsState(
        targetValue = if (isOpen) 0f else 180f,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
    )

    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .clickable {
                isOpen = !isOpen
                if (isOpen) showDialog = true
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 8f
            val w = size.width
            val h = size.height

            drawArc(
                color = Color.White,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true
            )

            drawArc(
                color = Color.Red,
                startAngle = 180f,
                sweepAngle = sweepAngle,
                useCenter = true
            )

            drawArc(
                color = Color.Black,
                startAngle = 180f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )


            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )

            drawLine(
                color = Color.Black,
                start = Offset(0f, h / 2),
                end = Offset(w, h / 2),
                strokeWidth = strokeWidth
            )

            drawCircle(
                color = Color.Black,
                radius = w / 6,
                center = Offset(w / 2, h / 2)
            )

            drawCircle(
                color = colorCircleCenter,
                radius = w / 9,
                center = Offset(w / 2, h / 2)
            )
        }
    }

    if (showDialog) {
        colorCircleCenter = Color.Red

        colorCircleCenter = Color.Red
        MyCustomAlertDialog(
            description = description,
            onConfirm = {
                showDialog = false
                isOpen = false
            }
        )
    }
}

@Composable
fun MyCustomAlertDialog(
    description: String,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        title = {
            Text(
                text = DESCRIPTION_OF_POKEMON,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red
            )
        },
        text = {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(CLOSE, color = Color.White)
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Color.Cyan.copy(alpha = 0.7f),
        tonalElevation = 8.dp
    )
}



@Composable
fun PokeballCardViewPokemon(
    modifier: Modifier = Modifier,
    onToggle: (Boolean) -> Unit
) {
    var isOpen by remember { mutableStateOf(false) }
    var colorCircleCenter = if (isOpen) Color.Red else Color.White

    val sweepAngle by animateFloatAsState(
        targetValue = if (isOpen) 0f else 180f,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing)
    )

    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .clickable {
                isOpen = !isOpen
                colorCircleCenter = if (isOpen) Color.Red else Color.White
                onToggle(isOpen)
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 8f
            val w = size.width
            val h = size.height

            drawArc(color = Color.White, startAngle = 0f, sweepAngle = 180f, useCenter = true)
            drawArc(color = Color.Red, startAngle = 180f, sweepAngle = sweepAngle, useCenter = true)
            drawArc(
                color = Color.Black,
                startAngle = 180f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = strokeWidth)
            )
            drawLine(
                color = Color.Black,
                start = Offset(0f, h / 2),
                end = Offset(w, h / 2),
                strokeWidth = strokeWidth
            )
            drawCircle(color = Color.Black, radius = w / 6, center = Offset(w / 2, h / 2))
            drawCircle(color = colorCircleCenter, radius = w / 9, center = Offset(w / 2, h / 2))
        }
    }
}

@Composable
fun AnimatedInvertedTriangleShape(
    modifier: Modifier = Modifier,
    startColor: Color = Color.Cyan.copy(alpha = 0.6f),
    endColor: Color = Color.Transparent,
    isVisible: Boolean
) {
    val progress by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 800, easing = LinearOutSlowInEasing)
    )

    Canvas(modifier = modifier.size(150.dp)) {
        val tip = Offset(size.width / 2f, size.height + (size.height * 0.4f))


        val baseY = size.height * (1f - 0.95f * progress)
        val baseWidth = size.width * (2f * progress)

        val leftBase = Offset(tip.x - baseWidth / 2f, baseY)
        val rightBase = Offset(tip.x + baseWidth / 2f, baseY)

        val path = Path().apply {
            moveTo(tip.x, tip.y)
            lineTo(leftBase.x, leftBase.y)
            lineTo(rightBase.x, rightBase.y)
            close()
        }

        val gradient = Brush.linearGradient(
            colors = listOf(startColor, endColor),
            start = tip,
            end = Offset(tip.x, baseY)
        )

        drawPath(path = path, brush = gradient)
    }
}