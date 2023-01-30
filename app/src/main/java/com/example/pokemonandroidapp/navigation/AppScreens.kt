package com.example.pokemonandroidapp.navigation

// CREAMOS UNA SEALED CLASS PARA CONTENER LOS OBJETOS DE CADA RUTA
sealed class AppScreens(val route: String){
    object InitialScreen: AppScreens("InitialScreen")
    object SavePokemon: AppScreens("SavePokemon")
    object ListPokemon: AppScreens("ListPokemon")
    }
