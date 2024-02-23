package com.essoft.recipemaker.ui.presentation.add

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.ui.theme.PoppinsFonts
import com.essoft.recipemaker.ui.theme.Primary100
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddTab(
    ingredient: String = "",
    instruction: String = "",
    selectedTab: Int = 0,
    onTabClick:(Int) -> Unit = {},
    onIngredientChange: (String) -> Unit = {},
    onInstructionChange: (String) -> Unit = {},
) {
    val tabs = listOf("Ingredient", "Procedure")
    val pagerState = rememberPagerState { tabs.size }
    //var ingredient by remember { mutableStateOf("") }
    //var instruction by remember { mutableStateOf("") }
    // coroutine scope
    val coroutineScope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                Box(
                    modifier = Modifier
                        .height(33.dp)
                        .width(150.dp)
                        .clickable(onClick = {
                            onTabClick(tabs.indexOf(tab))
                            coroutineScope.launch {
                                pagerState.scrollToPage(tabs.indexOf(tab))
                            }
                        })
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (selectedTab == tabs.indexOf(tab)) Color(0xFF129575) else Color.White
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab,
                        color = if (selectedTab == tabs.indexOf(tab)) Color.White else Color(0xFF129575),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = PoppinsFonts,
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

        Spacer(modifier = Modifier.padding(top = 13.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            when(index) {
                0 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            value = ingredient,
                            onValueChange = {
                                //ingredient = it
                                onIngredientChange(it)
                            },
                            label = { Text(text = "", fontSize = 14.sp) },
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(35.dp)
                                )
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(bottom = 120.dp),
                            textStyle = TextStyle(
                                fontFamily = PoppinsFonts,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W400
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Primary100,
                                containerColor = Color.Transparent,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                capitalization = KeyboardCapitalization.Sentences,
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() },
                                onNext = { focusManager.clearFocus() }
                            ),
                        )
                    }

                    onTabClick(pagerState.currentPage)
                }

                1 -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            value = instruction,
                            onValueChange = {
                                //instruction = it
                                onInstructionChange(it)
                            },
                            label = { Text(text = "", fontSize = 14.sp) },
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .padding(bottom = 120.dp),
                            textStyle = TextStyle(
                                fontFamily = PoppinsFonts,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Primary100,
                                containerColor = Color.Transparent,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                capitalization = KeyboardCapitalization.Sentences,
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() },
                                onNext = { focusManager.clearFocus() }
                            ),
                        )
                    }

                    onTabClick(pagerState.currentPage)
                }
            }
        }
    }
}