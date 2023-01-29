package com.example.pokemonandroidapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonandroidapp.screens.*
import com.example.pokemonandroidapp.viewModel.EditPokemonViewModel
import com.example.pokemonandroidapp.viewModel.ListPokemonViewModel
import com.example.pokemonandroidapp.viewModel.SavePokemonViewModel


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.InitialScreen.route) {
        composable(AppScreens.InitialScreen.route) { InitialScreen(navigationController) }
        composable(AppScreens.SavePokemon.route) { SavePokemon(navigationController, SavePokemonViewModel()) }
        composable(AppScreens.EditPokemon.route) { EditPokemon(navigationController, EditPokemonViewModel()) }
        composable(AppScreens.ListPokemon.route) { ListPokemon(navigationController, ListPokemonViewModel()) }
    }
}