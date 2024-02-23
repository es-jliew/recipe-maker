package com.essoft.recipemaker.ui.presentation.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.R
import com.essoft.recipemaker.ui.theme.Grey2
import com.essoft.recipemaker.ui.theme.Grey4

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeSearchBar() {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        enabled = false,
        placeholder = {
            Text(
                "Search recipe",
                color = Grey2,
                fontSize = 12.sp) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Grey2) },
        shape = RoundedCornerShape(10.dp),
        colors = SearchBarDefaults.colors(
            containerColor = Grey4,
        ),
    ) {}
}