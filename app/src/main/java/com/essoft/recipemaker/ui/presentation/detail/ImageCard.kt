package com.essoft.recipemaker.ui.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Preview
@Composable
fun ImageCard(imageUri: String = "") {
    //Log.d("Uri", imageUri)
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest
                .Builder(LocalContext.current)
                .data(data = imageUri.toUri())
                .build(),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.Crop)
        }
    }
}