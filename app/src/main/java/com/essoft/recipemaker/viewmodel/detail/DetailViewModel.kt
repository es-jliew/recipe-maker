package com.essoft.recipemaker.viewmodel.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.repo.IRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val recipeRepository: IRecipeRepository): ViewModel() {
    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    fun updateRecipe(recipe: RecipeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(recipe = recipe)}
        }
    }

    private fun deleteRecipe(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (recipeRepository.deleteRecipe(id)) {
                    _uiState.apply {
                        update { it.copy(showSnackbar = true) }
                        update { it.copy(snackbarMessage = "Recipe deleted.")}
                    }
                    Log.d("Delete", "Success")
                } else {
                    _uiState.apply {
                        update { it.copy(showSnackbar = true) }
                        update { it.copy(snackbarMessage = "Recipe not deleted. Please try again later.")}
                    }
                    Log.d("Delete", "Fail")
                }
            } catch( _ : Exception) {

            }
        }
    }

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.UpdateSelectedTab -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(selectedTabIndex = event.index) }
                }
            }

            is DetailEvent.DeleteRecipe -> {
                deleteRecipe(event.id)
            }

            is DetailEvent.UpdateShowSnackbar -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(showSnackbar = event.show) }
                }
            }
        }
    }
}

data class DetailState(
    val recipe: RecipeModel= RecipeModel(),
    val selectedTabIndex: Int = 0,
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = "",
)