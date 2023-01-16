package com.example.pokemonandroidapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonandroidapp.screens.*


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.InitialScreen.route) {
        composable(AppScreens.InitialScreen.route) { InitialScreen(navigationController) }
        composable(AppScreens.SavePokemon.route) { SavePokemon(navigationController) }
        composable(AppScreens.EditPokemon.route) {EditPokemon(navigationController) }
        composable(AppScreens.ListPokemon.route) {ListPokemon(navigationController) }
    }
}