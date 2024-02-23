package com.essoft.recipemaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.essoft.recipemaker.ui.presentation.NavGraphs
import com.essoft.recipemaker.ui.theme.RecipeMakerTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeMakerTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}