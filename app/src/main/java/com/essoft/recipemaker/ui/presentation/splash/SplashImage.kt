package com.essoft.recipemaker.ui.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.essoft.recipemaker.R

@Preview
@Composable
fun SplashImage() {
    Image(
        painter = painterResource(id = R.drawable.img_splash),
        contentDescription = "Background Image"
    )
}