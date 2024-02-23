package com.essoft.recipemaker.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.essoft.recipemaker.ui.theme.Primary100

@Composable
fun MediumButton(onClick: () -> Unit = {}, stringId: Int, modifier: Modifier) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(
                color = Primary100,
                shape = RoundedCornerShape(10.dp)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = stringId),
                color = Color.White,
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

            Spacer(Modifier.padding(start = 16.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Right Arrow Icon"
            )
        }

    }
}

@Preview
@Composable
fun PreviewMediumButton() {
    MediumButton({}, R.string.button_button, modifier = Modifier)
}