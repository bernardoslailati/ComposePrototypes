package com.localiza.jdc.composeprototypes.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.localiza.jdc.composeprototypes.ui.screen.data.Planet
import com.localiza.jdc.composeprototypes.ui.theme.ComposePrototypesTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainSharedTransitionScreen(modifier: Modifier = Modifier) {
    var showDetails by remember { mutableStateOf(false) }
    var selectedPlanet by remember { mutableStateOf<Planet>(Planet.Mercury) }

    SharedTransitionLayout(modifier = modifier) {
        AnimatedContent(
            targetState = showDetails,
            label = "Main shared planet transition"
        ) { targetState ->
            if (!targetState)
                HomeScreen(
                    initialPage = selectedPlanet.number - 1,
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    onNavigateToPlanetDetails = { planet ->
                        selectedPlanet = planet
                        showDetails = true
                    }
                )
            else
                PlanetDetailsScreen(
                    planet = selectedPlanet,
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    onNavigateBack = {
                        showDetails = false
                    }
                )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ComposePrototypesTheme {
       // MainScreen()
    }
}