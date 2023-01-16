package com.example.pokemonandroidapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemonandroidapp.model.Pokemon
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ListPokemon(navController: NavHostController) {

    val collectionPath = "pokemon"
    val db = FirebaseFirestore.getInstance()
    var data = rememberSaveable { mutableListOf<Pokemon>() }
    var message = ""
    val errorMessage = "No existen datos"
    val dbError = "La conexión a FireStore no se ha podido completar"
    val successMessage = "Se ha eliminado con exito"

    db.collection(collectionPath)
        .get()
        .addOnSuccessListener {
            data.clear()
            for (received in it) {
                val auxData = Pokemon(received.get("number") as String, received.get("name") as String, received.get("primaryType") as String, received.get("secondaryType") as String)
                data.add(auxData)
                //Log.i("DATOS:", datos)
            }

            if (data.isEmpty()) {
                message = errorMessage
            }
        }
        .addOnSuccessListener {
            message = successMessage
        }
        .addOnFailureListener {
            message = dbError
        }

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp)
            .padding(start = 10.dp)
            .padding(end = 10.dp)
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
                var delete = false
                PokemonBox(pokemon = pokemon, delete = delete) {
                        db.collection(collectionPath)
                            .document(pokemon.number.toString())
                            .delete()
                            .addOnSuccessListener {
                                message = successMessage
                            }
                            .addOnFailureListener {
                                message = errorMessage
                            }
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
            Text(text = pokemon.number.toString(), color = Color.Black)
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