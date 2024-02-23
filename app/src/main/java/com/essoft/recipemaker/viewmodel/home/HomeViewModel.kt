package com.essoft.recipemaker.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.model.RecipeType
import com.essoft.recipemaker.repo.IRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val recipeRepository: IRecipeRepository): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    private var originalRecipeList: List<RecipeModel> = listOf()

    fun retrieveAllRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                recipeRepository.retrieveAllRecipes().collect { response ->
                    Log.d("Response", response.toString())
                    originalRecipeList = response
                    _uiState.update { it.copy(recipes = response) }
                }
            } catch (_: Exception) {

            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.FilterRecipe -> {
                _uiState.update { it.copy(selectedRecipeType = event.recipeType) }
                when(event.recipeType) {
                    is RecipeType.ALL -> {
                        _uiState.update { it.copy(recipes = originalRecipeList)}
                    }
                    else -> {
                        _uiState.update { it -> it.copy(recipes = originalRecipeList.filter { it.type == event.recipeType.name })}
                    }
                }
            }
        }
    }
}

data class HomeState(
    val recipes: List<RecipeModel> = emptyList(),
    val selectedRecipeType: RecipeType = RecipeType.ALL,
)