package com.essoft.recipemaker.ui.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.ui.theme.PoppinsFonts

@Preview
@Composable
fun RecipeTitle(name: String = "Spicy chicken burger with Fr") {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.W800,
                fontFamily = PoppinsFonts,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
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