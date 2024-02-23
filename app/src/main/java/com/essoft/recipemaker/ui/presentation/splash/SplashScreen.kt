package com.essoft.recipemaker.ui.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.essoft.recipemaker.R
import com.essoft.recipemaker.ui.common.MediumButton
import com.essoft.recipemaker.ui.common.SubtitleText
import com.essoft.recipemaker.ui.common.TitleText
import com.essoft.recipemaker.ui.presentation.destinations.HomeScreenDestination
import com.essoft.recipemaker.ui.theme.RecipeMakerTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(navigator: DestinationsNavigator) {

    SplashScreenContent(navigator)
}

@Preview
@Composable
fun SplashScreenContent(navigator: DestinationsNavigator? = null) {
    RecipeMakerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(top = 56.dp))

                    SplashImage()

                    TitleText()

                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    SubtitleText()
                }

                MediumButton(
                    onClick = {
                        navigator?.navigate(HomeScreenDestination)
                    },
                    stringId = R.string.button_start_cooking,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}
