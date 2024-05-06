package com.localiza.jdc.composeprototypes.ui.screen.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.localiza.jdc.composeprototypes.ui.screen.data.Planet
import com.localiza.jdc.composeprototypes.ui.screen.data.planetList

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun PlanetHorizontalList(
    modifier: Modifier = Modifier,
    initialPage: Int = 0, onNavigateToPlanetDetails: (Planet) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val horizontalPagerState =
        rememberPagerState(initialPage = initialPage, pageCount = { planetList.size })
    val selectedItem by remember { derivedStateOf { horizontalPagerState.currentPage } }

    HorizontalPager(
        modifier = modifier,
        beyondBoundsPageCount = 2,
        contentPadding = PaddingValues(16.dp),
        pageSize = PageSize.Fixed(250.dp),
        pageSpacing = 32.dp,
        state = horizontalPagerState
    ) { page ->
        PlanetItem(
            planet = planetList[page],
            isHighlighted = selectedItem == planetList[page].number - 1,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            onNavigateToDetails = onNavigateToPlanetDetails
        )
    }
}