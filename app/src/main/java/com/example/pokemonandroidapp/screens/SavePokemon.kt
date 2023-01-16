package com.example.pokemonandroidapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import com.example.pokemonandroidapp.ui.theme.*
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

            var expanded by remember { mutableStateOf(false) }
            val primaryTypes = listOf(
                "Normal", "Fuego", "Agua", "Eléctrico", "Planta", "Hielo", "Lucha", "Veneno", "Tierra", "Volador", "Psíquico", "Bicho", "Roca", "Fantasma", "Dragón", "Siniestro", "Acero", "Hada"
            )
            val secondaryTypes = listOf(
                "Normal", "Fuego", "Agua", "Eléctrico", "Planta", "Hielo", "Lucha", "Veneno", "Tierra", "Volador", "Psíquico", "Bicho", "Roca", "Fantasma", "Dragón", "Siniestro", "Acero", "Hada", "No tiene"
            )


            var textFieldSizeDropDownMenu by remember { mutableStateOf(Size.Zero)}

            val icon = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSizeDropDownMenu = coordinates.size.toSize()
                    },
                value = pokemonPrimaryType,
                onValueChange = {  },
                readOnly = true,
                label = {Text("Introduce el tipo principal", Modifier.clickable { expanded = !expanded })},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFieldSizeDropDownMenu.width.toDp()})
            ) {
                primaryTypes.forEach { label ->
                    DropdownMenuItem(onClick = {
                        pokemonPrimaryType = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSizeDropDownMenu = coordinates.size.toSize()
                    },
                value = pokemonSecondaryType,
                onValueChange = {  },
                readOnly = true,
                label = {Text("Introduce el tipo secundario", Modifier.clickable { expanded = !expanded })},
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFieldSizeDropDownMenu.width.toDp()})
            ) {
                secondaryTypes.forEach { label ->
                    DropdownMenuItem(onClick = {
                        pokemonSecondaryType = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }

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