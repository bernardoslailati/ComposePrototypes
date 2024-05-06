package com.localiza.jdc.composeprototypes.ui.screen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.localiza.jdc.composeprototypes.R

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(0))
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SideMenuIcon()
        MyProfileImage()
    }
}

@Composable
fun SideMenuIcon(modifier: Modifier = Modifier) {
    IconButton(modifier = modifier.size(72.dp), onClick = {}) {
        Icon(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.ic_side_menu),
            tint = Color.White,
            contentDescription = "Side menu icon"
        )
    }
}

@Composable
fun MyProfileImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(48.dp),
        painter = painterResource(id = R.drawable.ic_my_profile),
        contentScale = ContentScale.Crop,
        contentDescription = "My profile image"
    )
}