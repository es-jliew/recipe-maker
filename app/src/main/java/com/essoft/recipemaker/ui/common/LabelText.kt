package com.essoft.recipemaker.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.ui.theme.PoppinsFonts

@Preview
@Composable
fun LabelText(stringId: Int = 0) {
    Text(text = stringResource(id = stringId),
        color = Color(0xFF121212),
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        fontFamily = PoppinsFonts,
        textAlign = TextAlign.Center,
        //Remove uneven padding in Text Composable
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
}