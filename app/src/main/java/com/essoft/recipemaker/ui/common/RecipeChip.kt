package com.essoft.recipemaker.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.ui.theme.PoppinsFonts
import com.essoft.recipemaker.ui.theme.Primary100

@Preview
@Composable
fun RecipeChip(
    name: String = "Chip",
    isSelected: Boolean = true,
    onSelectionChanged: (String) -> Unit = {}) {

    Surface(
        modifier = Modifier.padding(0.dp),
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) Primary100 else Color.White
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 8.dp),
                color = if(isSelected) Color.White else Primary100,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                fontFamily = PoppinsFonts,
                //Remove uneven padding in Text Composable
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}