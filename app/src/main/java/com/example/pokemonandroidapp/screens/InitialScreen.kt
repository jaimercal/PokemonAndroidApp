package com.example.pokemonandroidapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pokemonandroidapp.R

@Composable
fun InitialScreen(navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
                .padding(start = 10.dp)
                .padding(end = 10.dp)

        ) {



            Image(
                    painter = painterResource(R.drawable.pokemonlogo),
                    contentDescription = "LogoFirebase",
                    modifier = Modifier.size(450.dp)
                )



            Button(
                onClick = {
                    navController.navigate("SavePokemon")


                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {
               Text(text = "Alta")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("EditPokemon")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {
                Text(text = "Modificar")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("BorrarCliente")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {
                Text(text = "Borrar")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("ListPokemon")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.Black)
            )
            {
                Text(text = "Consultar")
            }

        }



    }
}

@Preview (showBackground = true)
@Composable
fun InitialScreen () {
}