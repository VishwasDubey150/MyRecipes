package com.example.myrecipes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Error

class MainViewModel:ViewModel() {
    private val _categoriesState = mutableStateOf(ReceipeState())
    val categoriesState : State<ReceipeState> = _categoriesState

    init {
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value=_categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }
            catch (e:Exception){
                _categoriesState.value=_categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class ReceipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}