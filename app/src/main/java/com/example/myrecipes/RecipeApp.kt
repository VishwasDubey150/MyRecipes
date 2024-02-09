package com.example.myrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import java.security.AccessController

@Composable
fun RecipeApp(navController: NavHostController) {

    val recipeViewModel:MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

        NavHost(navController = navController, startDestination = Screen.ReceipeScreen.route ){
        composable(route = Screen.ReceipeScreen.route){
            RecipeScreen(viewstate = viewstate,navigateToDetail =
            {

                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

            composable(route = Screen.DetailScreen.route)
            {
                val category = navController.previousBackStackEntry?.savedStateHandle?.
                        get<Category>("cat")?: Category("","","","")
                CategoryDetailscreen(category = category)
            }
    }
}