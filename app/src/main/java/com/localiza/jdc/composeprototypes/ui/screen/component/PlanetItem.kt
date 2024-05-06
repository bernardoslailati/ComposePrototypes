package com.localiza.jdc.composeprototypes.ui.screen.component

import android.graphics.BlurMaskFilter
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.localiza.jdc.composeprototypes.R
import com.localiza.jdc.composeprototypes.ui.screen.data.Planet
import com.localiza.jdc.composeprototypes.ui.theme.ComposePrototypesTheme
import com.localiza.jdc.composeprototypes.ui.theme.Typography

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlanetItem(
    modifier: Modifier = Modifier,
    planet: Planet,
    isHighlighted: Boolean = false,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigateToDetails: (Planet) -> Unit
) {
    val planetSize: Dp by animateDpAsState(
        targetValue = if (isHighlighted) 150.dp else 100.dp, label = "Animated planet size",
    )
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-84).dp)
    ) {
        with(sharedTransitionScope) {
            Image(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = "image/${planet.imageRes}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(planetSize)
                    .drawBehind {
                        drawIntoCanvas { canvas ->
                            val paint = Paint()
                            val frameworkPaint = paint.asFrameworkPaint()
                            frameworkPaint.maskFilter =
                                (BlurMaskFilter(36f, BlurMaskFilter.Blur.NORMAL))
                            frameworkPaint.color = Color.Cyan
                                .copy(alpha = 0.15f)
                                .toArgb()

                            canvas.drawCircle(
                                radius = planetSize.toPx() / 1.8f,
                                center = Offset(x = size.width / 2f, y = size.height / 2f),
                                paint = paint
                            )
                        }
                    },
                painter = painterResource(id = planet.imageRes),
                alpha = 0.95f,
                contentDescription = "Planet image"
            )
            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 280.dp)
                    .padding(16.dp)
                    .zIndex(-8f)
                    .clip(RoundedCornerShape(48.dp))
                    .background(color = Color.White),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "number/${planet.number}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                        .alpha(0.15f),
                    text = planet.number.toString(),
                    style = Typography.titleSmall.copy(
                        fontSize = 128.sp,
                        fontWeight = FontWeight.Black
                    )
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        modifier = Modifier.sharedBounds(
                            rememberSharedContentState(key = "name/${planet.name}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                        text = planet.name,
                        style = Typography.titleSmall.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.sharedBounds(
                            rememberSharedContentState(key = "shortDescription/${planet.shortDescription.hashCode()}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                        text = planet.shortDescription,
                        style = Typography.bodySmall.copy(
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Image(
                modifier = Modifier
                    .size(128.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        Log.d("PlanetItem", "PlanetItem: clicked ${planet.name}")
                        onNavigateToDetails(planet)
                    },
                painter = painterResource(id = R.drawable.ic_more_details),
                alpha = 0.95f,
                contentDescription = "Planet image"
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun PlanetItemPreview() {
    ComposePrototypesTheme {
        val showDetails by remember { mutableStateOf(false) }
        SharedTransitionLayout {
            AnimatedContent(
                targetState = showDetails,
                label = "Main shared planet transition"
            ) { targetState ->
                if (!targetState)
                    PlanetItem(planet = Planet.Venus,
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        onNavigateToDetails = {}
                    )
            }
        }
    }
}