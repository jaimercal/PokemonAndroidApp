package com.example.pokemonandroidapp.navigation

// CREAMOS UNA SEALED CLASS PARA CONTENER LOS OBJETOS DE CADA RUTA
sealed class AppScreens(val ruta: String){
    object MenuInicio: AppScreens("MenuInicio")
    object GuardarPokemon: AppScreens("GuardarPokemon")
    object ModificarPokemon: AppScreens("ModificarPokemon")
    object BorrarPokemon: AppScreens("BorrarPokemon")
    object ConsultarPokemon: AppScreens("ConsultarPokemon")
    }
