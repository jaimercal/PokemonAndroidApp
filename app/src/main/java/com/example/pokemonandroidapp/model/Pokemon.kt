package com.example.pokemonandroidapp.model

import androidx.compose.ui.graphics.Color
import androidx.dynamicanimation.animation.FlingAnimation
import com.example.pokemonandroidapp.ui.theme.*

class Pokemon(var number: String, var name: String, var primaryType: String, var secondaryType: String) {
    val typeColors = mapOf<String, Color>("Normal" to Normal100, "Fuego" to Fire100, "Agua" to Water100, "Eléctrico" to Electric100, "Planta" to Grass100, "Hielo" to Ice100, "Lucha" to Fighting100, "Veneno" to Poison100, "Tierra" to Ground100, "Volador" to Flying100, "Psíquico" to Psychic100, "Bicho" to Bug100, "roca" to Rock100, "Fantasma" to Ghost100, "Dragón" to Dragon100, "Siniestro" to Dark100, "acero" to Steel100, "Hada" to Fairy100, "No tiene" to None100)

    fun getPrimaryColor(): Color? {
        return typeColors[primaryType]
    }

    fun getSecondaryColor(): Color? {
        return typeColors[secondaryType]
    }
}