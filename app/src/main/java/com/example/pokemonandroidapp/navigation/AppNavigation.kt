package com.example.pokemonandroidapp.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonandroidapp.screens.*


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.MenuInicio.ruta) {
        composable(AppScreens.MenuInicio.ruta) { MenuInicio(navigationController) }
        composable(AppScreens.GuardarPokemon.ruta) {GuardarPokemon(navigationController) }
        composable(AppScreens.ModificarPokemon.ruta) {ModificarPokemon(navigationController) }
        composable(AppScreens.BorrarPokemon.ruta) { BorrarPokemon(navigationController) }
        composable(AppScreens.ConsultarPokemon.ruta) {ConsultarPokemon(navigationController) }
    }
}