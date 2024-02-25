package com.essoft.recipemaker.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.repo.IRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val recipeRepository: IRecipeRepository): ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.DeleteRecipe -> {
                deleteRecipe(event.id)
            }

            is DetailEvent.SetupUi -> {
                setupUi(event.dbRecipe)
            }

            is DetailEvent.UpdateSelectedTab -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(selectedTabIndex = event.index) }
                }
            }

            is DetailEvent.ShowSnackbar -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiState.update { it.copy(showSnackbar = event.show) }
                }
            }
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
                } else {
                    _uiState.apply {
                        update { it.copy(showSnackbar = true) }
                        update { it.copy(snackbarMessage = "Recipe not deleted. Please try again later.")}
                    }
                }
            } catch( _ : Exception) {

            }
        }
    }

    private fun setupUi(dbRecipe: DbRecipeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(dbRecipe = dbRecipe)}
        }
    }
}

data class DetailUiState(
    val dbRecipe: DbRecipeModel = DbRecipeModel(),
    val selectedTabIndex: Int = 0,
    val showSnackbar: Boolean = false,
    val snackbarMessage: String = "",
)