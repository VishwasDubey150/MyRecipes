package com.example.myrecipes

sealed class Screen (val route:String){
    object ReceipeScreen : Screen("recipesscreen")
    object DetailScreen : Screen("detailscreen")
}