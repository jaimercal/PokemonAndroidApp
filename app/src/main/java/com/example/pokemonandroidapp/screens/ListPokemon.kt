package com.example.pokemonandroidapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemonandroidapp.model.Pokemon
import com.example.pokemonandroidapp.viewModel.ListPokemonViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ListPokemon(navController: NavHostController, listPokemonViewModel: ListPokemonViewModel) {
    val data by listPokemonViewModel.pokemon.observeAsState(mutableListOf())

    listPokemonViewModel.loadPokemon()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 10.dp, end = 10.dp)
    ) {
        Text(
            text = "Listado de Pokemon",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
        ) {
            data.forEach { pokemon ->
                PokemonBox(pokemon = pokemon, delete = true) {
                    listPokemonViewModel.deletePokemon(pokemon.number)
                }
            }
        }
    }
}

@Composable
fun PokemonBox(pokemon: Pokemon, delete: Boolean, deleteButtonAction: () -> Unit) {
    Spacer(modifier = Modifier.size(15.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = pokemon.getPrimaryColor()!!, shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = pokemon.number, color = Color.Black)
            Text(text = pokemon.name, color = Color.Black)
            Text(text = pokemon.primaryType, color = Color.Black)
            Text(text = pokemon.secondaryType, color = Color.Black)
        }
        if (delete){
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Button(onClick = deleteButtonAction, modifier = Modifier.width(100.dp)) {
                    Text(text = "Borrar")
                }
            }
        }
    }
}