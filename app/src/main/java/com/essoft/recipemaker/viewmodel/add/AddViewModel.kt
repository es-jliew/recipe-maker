package com.essoft.recipemaker.viewmodel.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.model.RecipeType
import com.essoft.recipemaker.repo.IRecipeRepository
import com.essoft.recipemaker.utils.FormatValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddViewModel(private val recipeRepository: IRecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AddUiState())
    val uiState: StateFlow<AddUiState> = _uiState

    private lateinit var tempUri: String

    fun onEvent(event: AddEvent) {
        when(event) {
            is AddEvent.UpdateImageUri -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(uri = event.uri) }
                }
            }
            is AddEvent.UpdateRecipeName -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(name = event.name) }
                }
            }
            is AddEvent.UpdateRecipeType -> {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d("Eugene", event.type)
                    _uiState.update { it.copy(selectedRecipeType = RecipeType.getRecipeType(event.type)) }
                    _uiState.update { it.copy(type = event.type) }
                }
            }
            is AddEvent.UpdateRecipeIngredient -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(ingredient = event.ingredient) }
                }
            }
            is AddEvent.UpdateRecipeInstruction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(instruction = event.instruction) }
                }
            }

            is AddEvent.UpdateSelectedTab -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(selectedTabIndex = event.index) }
                }
            }

            is AddEvent.ShowSnackbar -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(showSnackbar = event.show) }
                }
            }

            is AddEvent.SetupUi -> {
                setupUi(event.dbRecipe)
            }

            is AddEvent.SaveRecipe -> {
                saveRecipe()
            }
        }
    }

    private fun createRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val dbRecipe = DbRecipeModel(
                    imageUri = uiState.value.uri,
                    name = uiState.value.name,
                    type = uiState.value.type,
                    ingredients = uiState.value.ingredient,
                    instructions = uiState.value.instruction
                )

                val savedImageUri = async(Dispatchers.IO) {
                    recipeRepository.saveRecipeImageToLocal(dbRecipe.imageUri)
                }.await()

                dbRecipe.imageUri = savedImageUri

                recipeRepository.createRecipe(dbRecipe)

                _uiState.apply {
                    update { it.copy(showSnackbar = true) }
                    update { it.copy(snackbarMessage = "Recipe added.")}
                }
            } catch (_: Exception) {
                _uiState.apply {
                    update { it.copy(showSnackbar = true) }
                    update { it.copy(snackbarMessage = "Recipe not added. Please try again.")
                    }
                }
            }
        }
    }

    private fun saveRecipe() {
        if (FormatValidator.isValidRecipe(uiState.value)) {
            if (uiState.value.id > 0) {
                updateRecipe()
            } else {
                createRecipe()
            }
        } else {
            _uiState.apply {
                update { it.copy(showSnackbar = true) }
                update { it.copy(snackbarMessage = "Incomplete Info. Please try again.") }
            }
        }
    }

    private fun setupUi(recipe: DbRecipeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.apply {
                update { it.copy(id = recipe.id) }
                update { it.copy(name = recipe.name) }
                update { it.copy(uri = recipe.imageUri) }
                update { it.copy(selectedRecipeType = RecipeType.getRecipeType(recipe.type))}
                update { it.copy(type = recipe.type) }
                update { it.copy(ingredient = recipe.ingredients) }
                update { it.copy(instruction = recipe.instructions) }
            }

            tempUri = recipe.imageUri
        }
    }

    private fun updateRecipe() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val recipe = RecipeModel(
                    id = uiState.value.id,
                    imageUri = uiState.value.uri,
                    name = uiState.value.name,
                    type = uiState.value.type,
                    ingredients = uiState.value.ingredient,
                    instructions = uiState.value.instruction,
                )

                if (tempUri != uiState.value.uri) {
                    val savedImageUri = async(Dispatchers.IO) {
                        recipeRepository.saveRecipeImageToLocal(uiState.value.uri)
                    }.await()

                    recipe.imageUri = savedImageUri
                }

                recipeRepository.updateRecipe(recipe)

                _uiState.apply {
                    update { it.copy(showSnackbar = true) }
                    update { it.copy(snackbarMessage = "Recipe updated.")}
                }
            }
        } catch ( _: Exception) {
            _uiState.apply {
                update { it.copy(showSnackbar = true) }
                update { it.copy(snackbarMessage = "Recipe not updated. Please try again.")
                }
            }
        }
    }
}

data class AddUiState(
    val selectedRecipeType: RecipeType = RecipeType.MAIN,
    val selectedTabIndex: Int = 0,
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = "",
    val id: Long = -1, // Id only for non entity recipe
    val name: String = "",
    val type: String = RecipeType.MAIN.name,
    val uri: String = "",
    val ingredient: String = "",
    val instruction: String = ""
)