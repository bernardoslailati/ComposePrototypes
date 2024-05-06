package com.localiza.jdc.composeprototypes.ui.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.localiza.jdc.composeprototypes.R
import com.localiza.jdc.composeprototypes.ui.screen.component.HomeFooter
import com.localiza.jdc.composeprototypes.ui.screen.component.HomeHeader
import com.localiza.jdc.composeprototypes.ui.screen.component.PlanetHorizontalList
import com.localiza.jdc.composeprototypes.ui.screen.data.Planet
import com.localiza.jdc.composeprototypes.ui.theme.ComposePrototypesTheme
import com.localiza.jdc.composeprototypes.ui.theme.Typography

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigateToPlanetDetails: (Planet) -> Unit
) {
    Column(
        modifier = modifier
            .animateContentSize(animationSpec = tween(0))
            .fillMaxSize()
            .animateContentSize(animationSpec = tween(0))
            .paint(
                painter = painterResource(id = R.drawable.ic_space),
                contentScale = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HomeHeader()
        HomeTitle()
        PlanetHorizontalList(
            initialPage = initialPage,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            onNavigateToPlanetDetails = onNavigateToPlanetDetails
        )
        HomeFooter()
    }
}

@Composable
fun HomeTitle() {
    Text(
        modifier = Modifier.animateContentSize(animationSpec = tween(0))
            .fillMaxWidth()
            .padding(horizontal = 36.dp),
        textAlign = TextAlign.Center,
        text = "Space",
        style = Typography.titleLarge.copy(color = Color.White, fontSize = 54.sp)
    )
    Text(
        modifier = Modifier
            .fillMaxWidth().animateContentSize(animationSpec = tween(0))
            .padding(horizontal = 36.dp),
        textAlign = TextAlign.Center,
        text = "Chronicles",
        style = Typography.titleLarge.copy(color = Color.White, fontSize = 48.sp)
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ComposePrototypesTheme {
//        HomeScreen(onNavigateToPlanetDetails = {})
    }
}

