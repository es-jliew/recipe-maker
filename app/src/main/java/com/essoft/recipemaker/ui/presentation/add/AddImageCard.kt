package com.essoft.recipemaker.ui.presentation.add

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoAlbum
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.essoft.recipemaker.R
import com.essoft.recipemaker.ui.theme.Grey4
import com.essoft.recipemaker.ui.theme.Primary80

@Preview(apiLevel = 33)
@Composable
fun AddImageCard(
    photoUri: String = "",
    onClick: (String) -> Unit = {}) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        onClick(uri.toString())
    }

    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if(photoUri.isNotEmpty()) Color.Transparent else Grey4,
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (photoUri.isNotEmpty()) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = photoUri)
                        .build(),
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.Crop)
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_photo),
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }

            Button(
                onClick = {
                    launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
                    .size(24.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(36.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(1.dp)
            ) {
                    Icon(
                        imageVector = Icons.Outlined.PhotoAlbum,
                        contentDescription = "Add Photo",
                        tint = Primary80,
                        modifier = Modifier.fillMaxSize()
                    )
            }
        }
    }
}