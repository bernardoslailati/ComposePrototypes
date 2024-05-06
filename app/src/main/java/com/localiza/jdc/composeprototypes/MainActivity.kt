package com.localiza.jdc.composeprototypes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.localiza.jdc.composeprototypes.ui.screen.MainSharedTransitionScreen
import com.localiza.jdc.composeprototypes.ui.theme.ComposePrototypesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ComposePrototypesTheme {
                MainSharedTransitionScreen(modifier = Modifier)
            }
        }
    }
}