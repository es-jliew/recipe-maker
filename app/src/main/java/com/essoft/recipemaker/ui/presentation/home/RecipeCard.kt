package com.essoft.recipemaker.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.ui.theme.PoppinsFonts

@Preview
@Composable
fun RecipeCard(recipe: DbRecipeModel = DbRecipeModel(name = "This Is a Test Recipe Name")) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest
                .Builder(LocalContext.current)
                .data(data = recipe.imageUri.toUri())
                .build(),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.Crop
            )

            Text(
                text = recipe.name,
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            0F to Color.Transparent,
                            0.2F to Color.Black.copy(alpha = 0.6F),
                            1F to Color.Black.copy(alpha = 0.9F)
                        )
                    )
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 16.dp),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                fontFamily = PoppinsFonts,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                minLines = 2,
                //Remove uneven padding in Text Composable
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}
