package com.essoft.recipemaker.ui.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.ui.common.RecipeChip
import com.essoft.recipemaker.ui.presentation.destinations.AddScreenDestination
import com.essoft.recipemaker.ui.presentation.destinations.HomeScreenDestination
import com.essoft.recipemaker.ui.theme.RecipeMakerTheme
import com.essoft.recipemaker.viewmodel.detail.DetailEvent
import com.essoft.recipemaker.viewmodel.detail.DetailState
import com.essoft.recipemaker.viewmodel.detail.DetailViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun DetailScreen(recipe: RecipeModel, navigator: DestinationsNavigator) {
    val viewModel: DetailViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateRecipe(recipe)
    }

    DetailScreenContent(
        uiState = uiState,
        onTabEvent = { viewModel.onEvent(DetailEvent.UpdateSelectedTab(it))},
        onDeleteEvent = { viewModel.onEvent(DetailEvent.DeleteRecipe(uiState.value.recipe.id))},
        onSnackbarEvent = { viewModel.onEvent(DetailEvent.UpdateShowSnackbar(it))},
        navigator = navigator
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
fun DetailScreenContent(
    uiState: State<DetailState>? = null,
    onTabEvent: (Int) -> Unit = {},
    onDeleteEvent: () -> Unit = {},
    onSnackbarEvent: (Boolean) -> Unit = {},
    navigator: DestinationsNavigator? = null
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    RecipeMakerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)) {
                Column {
                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    DetailAppBar(
                        onBackClick = { navigator?.navigate(HomeScreenDestination) },
                        onEditClick = { navigator?.navigate(AddScreenDestination(uiState?.value?.recipe!!)) },
                        onDeleteClick = { onDeleteEvent() }
                    )

                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    ImageCard(imageUri = uiState?.value?.recipe?.imageUri.toString())

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    Row {
                        RecipeChip(name = uiState?.value?.recipe?.type.toString())

                        Spacer(modifier = Modifier.padding(start = 12.dp))

                        RecipeTitle(name = uiState?.value?.recipe?.name.toString())
                    }

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    DetailTab(
                        ingredient = uiState?.value?.recipe?.ingredients.toString(),
                        instruction = uiState?.value?.recipe?.instructions.toString(),
                        selectedTab = uiState?.value?.selectedTabIndex!!,
                        onTabClick = { onTabEvent(it) }
                    )
                }

                if (uiState?.value?.showSnackbar == true) {
                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar (
                            message = uiState.value.snackbarMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                        when(result) {
                            SnackbarResult.ActionPerformed -> {
                                //Do Something
                                onSnackbarEvent(false)
                                navigator?.navigate(HomeScreenDestination)
                            }
                            SnackbarResult.Dismissed -> {
                                //Do Something
                                onSnackbarEvent(false)
                                navigator?.navigate(HomeScreenDestination)
                            }
                        }
                    }
                }

                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}