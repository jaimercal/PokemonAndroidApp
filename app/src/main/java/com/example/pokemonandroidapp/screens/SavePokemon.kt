package com.example.pokemonandroidapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore

@Composable

fun SavePokemon(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    val collectionName = "pokemon"
    var pokemonNumber by remember { mutableStateOf("") }
    var pokemonName by remember { mutableStateOf("") }
    var pokemonPrimaryType by remember { mutableStateOf("") }
    var pokemonSecondaryType by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 112.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.Blue)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .padding(start= 10.dp)
                .padding(end= 10.dp)

        ) {

            Text(
                text = "Guardar Pokemon",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = pokemonNumber,
                onValueChange = { pokemonNumber = it },
                label = { Text("Introduce el número") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = pokemonName,
                onValueChange = { pokemonName = it },
                label = { Text("Introduce el nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = pokemonPrimaryType,

                onValueChange = { pokemonPrimaryType = it },
                label = { Text("Introduce el tipo principal") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = pokemonSecondaryType,
                onValueChange = { pokemonSecondaryType = it },
                label = { Text("Introduce el tipo secundario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))
            val data = hashMapOf(
                "number" to pokemonNumber.toString(),
                "name" to pokemonName.toString().lowercase(),
                "primaryType" to pokemonPrimaryType.toString(),
                "secondaryType" to pokemonSecondaryType.toString()
            )

            var confirmationMessage by remember { mutableStateOf("") }

            Button(

                onClick = {
                    db.collection(collectionName)
                        .document(pokemonNumber)
                        .set(data)
                        .addOnSuccessListener {
                            confirmationMessage ="Datos guardados correctamente"
                            pokemonNumber =""
                            pokemonName=""
                            pokemonPrimaryType=""
                            pokemonSecondaryType=""
                        }
                        .addOnFailureListener {
                            confirmationMessage ="No se ha podido guardar"
                            pokemonNumber =""
                            pokemonName=""
                            pokemonPrimaryType=""
                            pokemonSecondaryType=""
                        }
                },

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {

                Text(text = "Guardar")


            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = confirmationMessage)
        }

    }
}