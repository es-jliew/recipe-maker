package com.essoft.recipemaker.ui.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.essoft.recipemaker.R
import com.essoft.recipemaker.model.RecipeType
import com.essoft.recipemaker.ui.common.MediumButton
import com.essoft.recipemaker.ui.common.RecipeChipGroup
import com.essoft.recipemaker.ui.presentation.destinations.AddScreenDestination
import com.essoft.recipemaker.ui.presentation.destinations.DetailScreenDestination
import com.essoft.recipemaker.ui.theme.RecipeMakerTheme
import com.essoft.recipemaker.viewmodel.home.HomeEvent
import com.essoft.recipemaker.viewmodel.home.HomeUiState
import com.essoft.recipemaker.viewmodel.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: HomeViewModel = koinViewModel()
    //Prevent onBackPressed to splash screen
    BackHandler {}

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.SetupUi)
    }

    val uiState = viewModel.uiState.collectAsState()

    HomeScreenContent(
        uiState = uiState.value,
        onTabCLick = viewModel::onEvent,
        navigator = navigator
    )
}

@Preview
@Composable
fun HomeScreenContent(
    uiState: HomeUiState = HomeUiState(),
    onTabCLick: (HomeEvent) -> Unit = {},
    navigator: DestinationsNavigator? = null
) {
    RecipeMakerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)) {
                Column {
                    Spacer(modifier = Modifier.padding(top= 20.dp))

                    TitleBar()

                    Spacer(modifier = Modifier.padding(top= 30.dp))

                    HomeSearchBar()

                    Spacer(modifier = Modifier.padding(top= 25.dp))

                    RecipeChipGroup(
                        recipeTypes = RecipeType.getAllRecipeTypes(),
                        selectedRecipeType = uiState.selectedRecipeType,
                        onSelectedChanged = { onTabCLick(HomeEvent.FilterRecipe(RecipeType.getRecipeType(it))) }
                    )

                    Spacer(modifier = Modifier.padding(top= 20.dp))

                    RecipeCardList(
                        recipes = uiState.currentDbRecipes,
                        onClick = { navigator?.navigate(DetailScreenDestination(recipe = it)) }
                    )
                }

                MediumButton(
                    { navigator?.navigate(AddScreenDestination(null)) }, //Pass null for for create new recipe
                    stringId = R.string.button_add_recipe,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}