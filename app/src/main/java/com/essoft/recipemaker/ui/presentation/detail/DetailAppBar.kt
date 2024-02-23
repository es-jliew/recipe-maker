package com.essoft.recipemaker.ui.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.essoft.recipemaker.R

@Preview
@Composable
fun DetailAppBar(
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Left Arrow Icon",
                modifier = Modifier.clickable(onClick = { onBackClick() }),
                tint = Color(0xFF121212)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit Icon",
                modifier = Modifier.clickable(onClick = { onEditClick()}),
                tint = Color(0xFF121212)
            )

            Spacer(modifier = Modifier.padding(start = 8.dp))

            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete Icon",
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = { onDeleteClick() }),
                tint = Color(0xFF121212)
            )
        }
    }
}