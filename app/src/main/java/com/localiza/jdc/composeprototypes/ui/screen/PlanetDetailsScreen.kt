package com.localiza.jdc.composeprototypes.ui.screen

import android.graphics.BlurMaskFilter
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.localiza.jdc.composeprototypes.R
import com.localiza.jdc.composeprototypes.ui.screen.data.Planet
import com.localiza.jdc.composeprototypes.ui.screen.data.planetList
import com.localiza.jdc.composeprototypes.ui.theme.ComposePrototypesTheme
import com.localiza.jdc.composeprototypes.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun getWindowSize(): DpSize {
    val configuration = LocalConfiguration.current
    return DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlanetDetailsScreen(
    modifier: Modifier = Modifier,
    planet: Planet,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onNavigateBack: () -> Unit
) {
    var targetPlanetSize by remember {
        mutableStateOf(0.dp)
    }

    LaunchedEffect(key1 = true) {
        delay(200L)
        targetPlanetSize = 500.dp
    }

    val planetSize by animateDpAsState(
        targetValue = targetPlanetSize,
        animationSpec = tween(durationMillis = 1_500),
        label = "Planet size"
    )
    val interactionSource = remember { MutableInteractionSource() }

    val windowSize = getWindowSize()
    val width = windowSize.width
    val height = windowSize.height

    Box {
        with(sharedTransitionScope) {
            Image(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image/${planet.imageRes}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(planetSize)
                    .align(Alignment.TopEnd)
                    .offset(x = width / 4, y = -height / 5)
                    .zIndex(8f),
                painter = painterResource(id = planet.imageRes),
                alpha = 0.95f,
                contentDescription = "Planet image"
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp),
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .size(48.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onNavigateBack()
                        },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Back button"
                )
                Text(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "number/${planet.number}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                        .alpha(0.15f)
                        .padding(16.dp),
                    text = planet.number.toString(),
                    style = Typography.titleSmall.copy(fontSize = 200.sp, fontWeight = FontWeight.Black)
                )
                Text(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "name/${planet.name}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    text = planet.name,
                    style = Typography.titleLarge.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.sharedBounds(
                        rememberSharedContentState(key = "shortDescription/${planet.shortDescription.hashCode()}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                    text = planet.longDescription,
                    style = Typography.bodyLarge.copy(fontSize = 13.sp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .drawBehind {
                                drawIntoCanvas { canvas ->
                                    val paint = Paint()
                                    val frameworkPaint = paint.asFrameworkPaint()
                                    frameworkPaint.maskFilter =
                                        (BlurMaskFilter(36f, BlurMaskFilter.Blur.NORMAL))
                                    frameworkPaint.color = Color(0xFF03adfc)
                                        .copy(alpha = 0.5f)
                                        .toArgb()

                                    canvas.drawRect(
                                        rect = Rect(
                                            offset = Offset(x = -8.dp.toPx(), y = -8.dp.toPx()),
                                            size = size.copy(
                                                width = size.width + 16.dp.toPx(),
                                                height = size.height + 16.dp.toPx()
                                            )
                                        ),
                                        paint = paint
                                    )
                                }
                            }
                            .clip(RoundedCornerShape(24.dp)),
                        painter = painterResource(id = R.drawable.ic_thumbnail),
                        contentDescription = "Back button"
                    )
                    Image(
                        modifier = Modifier
                            .size(128.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "Play button"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .fillMaxWidth().padding(bottom = 36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy((-24).dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        items(
                            items = planetList.map { it.imageRes },
                        ) { profileImageRes ->
                            Image(
                                modifier = Modifier.size(if (planet.imageRes == profileImageRes) 54.dp else 36.dp),
                                painter = painterResource(id = profileImageRes),
                                alpha = if (planet.imageRes == profileImageRes) 1f else 0.8f,
                                contentScale = ContentScale.Crop,
                                contentDescription = "Profile image 1"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PlanetDetailsScreenPreview() {
    ComposePrototypesTheme {
//        PlanetDetailsScreen(
//            planet = Planet.Mars, onNavigateBack = {}
//        )
    }
}