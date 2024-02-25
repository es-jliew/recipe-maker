package com.essoft.recipemaker.ui.presentation.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.essoft.recipemaker.R
import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.model.RecipeType
import com.essoft.recipemaker.ui.common.LabelText
import com.essoft.recipemaker.ui.common.MediumButton
import com.essoft.recipemaker.ui.common.RecipeChipGroup
import com.essoft.recipemaker.ui.presentation.destinations.HomeScreenDestination
import com.essoft.recipemaker.ui.theme.PoppinsFonts
import com.essoft.recipemaker.ui.theme.Primary100
import com.essoft.recipemaker.ui.theme.RecipeMakerTheme
import com.essoft.recipemaker.viewmodel.add.AddEvent
import com.essoft.recipemaker.viewmodel.add.AddUiState
import com.essoft.recipemaker.viewmodel.add.AddViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun AddScreen(recipe: DbRecipeModel?, navigator: DestinationsNavigator) {
    val viewModel: AddViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()

    if (recipe != null) {
        LaunchedEffect(Unit) {
            viewModel.onEvent(AddEvent.SetupUi(recipe))
        }
    }

    AddScreenContent(
        uiState = uiState.value,
        onUriChange = viewModel::onEvent,
        onTypeChange = viewModel::onEvent,
        onNameChange = viewModel::onEvent,
        onTabClick = viewModel::onEvent,
        onIngredientChange = viewModel::onEvent,
        onInstructionChange = viewModel::onEvent,
        onSaveClick = viewModel::onEvent,
        onSnackbarEvent = viewModel::onEvent,
        navigator
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddScreenContent(
    uiState: AddUiState = AddUiState(),
    onUriChange: (AddEvent) -> Unit = {},
    onTypeChange: (AddEvent) -> Unit = {},
    onNameChange: (AddEvent) -> Unit = {},
    onTabClick: (AddEvent) -> Unit = {},
    onIngredientChange: (AddEvent) -> Unit = {},
    onInstructionChange: (AddEvent) -> Unit = {},
    onSaveClick: (AddEvent) -> Unit = {},
    onSnackbarEvent: (AddEvent) -> Unit = {},
    navigator: DestinationsNavigator? = null
) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    RecipeMakerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    AddAppBar(
                        onClick = { navigator?.navigate(HomeScreenDestination) }
                    )

                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    AddImageCard(
                        photoUri = uiState.uri,
                        onClick = { onUriChange(AddEvent.UpdateImageUri(it)) }
                    )

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    LabelText(R.string.label_recipe_type)

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    RecipeChipGroup(
                        recipeTypes = RecipeType.getSubRecipeTypes(),
                        selectedRecipeType = uiState.selectedRecipeType,
                        onSelectedChanged = { onTypeChange(AddEvent.UpdateRecipeType(it)) }
                    )

                    Spacer(modifier = Modifier.padding(top = 12.dp))

                    LabelText(R.string.label_recipe_name)

                    OutlinedTextField(
                        value = uiState.name,
                        onValueChange = {
                            onNameChange(AddEvent.UpdateRecipeName(it))
                        },
                        label = { Text(text = "Name", fontSize = 14.sp) },
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .height(64.dp)
                            .fillMaxWidth(),
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

                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    AddTab(
                        ingredient = uiState.ingredient,
                        instruction = uiState.instruction,
                        selectedTab = uiState.selectedTabIndex,
                        onTabClick = { onTabClick(AddEvent.UpdateSelectedTab(it)) },
                        onIngredientChange = { onIngredientChange(AddEvent.UpdateRecipeIngredient(it)) },
                        onInstructionChange = { onInstructionChange(AddEvent.UpdateRecipeInstruction(it)) }
                    )
                }

                MediumButton(
                    onClick = { onSaveClick(AddEvent.SaveRecipe) },
                    stringId = R.string.button_save_recipe,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )

                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )

                if (uiState.showSnackbar) {
                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar (
                            message = uiState.snackbarMessage,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                        when(result) {
                            SnackbarResult.ActionPerformed -> {
                                //Do Something
                                onSnackbarEvent(AddEvent.ShowSnackbar(false))
                                navigator?.navigate(HomeScreenDestination)
                            }
                            SnackbarResult.Dismissed -> {
                                //Do Something
                                onSnackbarEvent(AddEvent.ShowSnackbar(false))
                                navigator?.navigate(HomeScreenDestination)
                            }
                        }
                    }
                }
            }
        }
    }
}