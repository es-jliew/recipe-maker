package com.essoft.recipemaker.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.R
import com.essoft.recipemaker.ui.theme.PoppinsFonts

@Preview
@Composable
fun SubtitleText() {
    Text(
        text = stringResource(id = R.string.text_subtitle),
        modifier = Modifier.padding(horizontal = 50.dp),
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
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