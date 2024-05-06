package com.localiza.jdc.composeprototypes.ui.screen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.localiza.jdc.composeprototypes.R
import com.localiza.jdc.composeprototypes.ui.theme.Typography

@Composable
fun HomeFooter() {
    Column(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(0))
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy((-24).dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            items(
                items = listOf(
                    R.drawable.ic_profile_1,
                    R.drawable.ic_profile_2,
                    R.drawable.ic_profile_3,
                    R.drawable.ic_profile_4
                )
            ) { profileImageRes ->
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = profileImageRes),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Profile image 1"
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Abdul, Karim and Khan are online.",
            style = Typography.bodyLarge.copy(color = Color.White)
        )
    }
}