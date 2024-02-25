package com.essoft.recipemaker.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.model.RecipeType
import com.essoft.recipemaker.repo.IRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val recipeRepository: IRecipeRepository): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private lateinit var fullDbRecipes: List<DbRecipeModel>

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.FilterRecipe -> {
                getFilteredRecipes(event.recipeType)
            }

            is HomeEvent.SetupUi -> {
                getAllRecipes()
            }
        }
    }

    private fun getAllRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                recipeRepository.getAllRecipes().collect { response ->
                    fullDbRecipes = response
                    _uiState.update { it.copy(currentDbRecipes = response) }
                }
            } catch (_: Exception) {

            }
        }
    }

    private fun getFilteredRecipes(recipeType: RecipeType) {
        when(recipeType) {
            is RecipeType.ALL -> {
                _uiState.update { it.copy(currentDbRecipes = fullDbRecipes) }
            }
            else -> {
                _uiState.update { it -> it.copy(currentDbRecipes = fullDbRecipes.filter { it.type == recipeType.name }) }
            }
        }

        _uiState.update { it.copy(selectedRecipeType = recipeType) }
    }
}

data class HomeUiState(
    val currentDbRecipes: List<DbRecipeModel> = emptyList(),
    val selectedRecipeType: RecipeType = RecipeType.ALL,
)