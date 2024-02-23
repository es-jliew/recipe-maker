package com.essoft.recipemaker.viewmodel.add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essoft.recipemaker.model.NonEntityRecipeModel
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.model.RecipeType
import com.essoft.recipemaker.repo.IRecipeRepository
import com.essoft.recipemaker.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddViewModel(private val recipeRepository: IRecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AddState())
    val uiState: StateFlow<AddState> = _uiState

    fun saveRecipe() {
        if (Validator.isValidRecipe(uiState.value)) {
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

    private fun createRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val recipe = RecipeModel(
                    imageUri = uiState.value.uri,
                    name = uiState.value.name,
                    type = uiState.value.type,
                    ingredients = uiState.value.ingredient,
                    instructions = uiState.value.instruction
                )

                recipeRepository.createRecipe(recipe)
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

    private fun updateRecipe() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val partialRecipe = NonEntityRecipeModel(
                    id = uiState.value.id,
                    imageUri = uiState.value.uri,
                    name = uiState.value.name,
                    type = uiState.value.type,
                    ingredients = uiState.value.ingredient,
                    instructions = uiState.value.instruction
                )

                recipeRepository.updateRecipe(partialRecipe)
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

    fun updateUiState(recipe: RecipeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.apply {
                update { it.copy(id = recipe.id) }
                update { it.copy(name = recipe.name) }
                update { it.copy(uri = recipe.imageUri.toString()) }
                update { it.copy(selectedRecipeType = RecipeType.getRecipeType(recipe.type.toString()))}
                update { it.copy(type = recipe.type.toString()) }
                update { it.copy(ingredient = recipe.ingredients) }
                update { it.copy(instruction = recipe.instructions) }
            }
        }
    }

    fun onEvent(event: AddEvent) {
        when(event) {
            is AddEvent.UpdateUri -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(uri = event.uri) }
                    Log.d("Eugene", event.uri)
                }
            }
            is AddEvent.UpdateName -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(name = event.name) }
                }
            }
            is AddEvent.UpdateType -> {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d("Eugene", event.type)
                    _uiState.update { it.copy(selectedRecipeType = RecipeType.getRecipeType(event.type)) }
                    _uiState.update { it.copy(type = event.type) }
                    Log.d("Eugene", _uiState.value.type)
                }
            }
            is AddEvent.UpdateIngredient -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(ingredient = event.ingredient) }
                }
            }
            is AddEvent.UpdateInstruction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(instruction = event.instruction) }
                }
            }

            is AddEvent.UpdateSelectedTab -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(selectedTabIndex = event.index) }
                }
            }

            is AddEvent.UpdateShowSnackbar -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(showSnackbar = event.show) }
                }
            }
        }
    }
}

data class AddState(
    val selectedRecipeType: RecipeType = RecipeType.MAIN,
    val selectedTabIndex: Int = 0,
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = "",
    val id: Long = -1, // Id only for query Objectbox
    val name: String = "",
    val type: String = RecipeType.MAIN.name,
    val uri: String = "",
    val ingredient: String = "",
    val instruction: String = ""
)