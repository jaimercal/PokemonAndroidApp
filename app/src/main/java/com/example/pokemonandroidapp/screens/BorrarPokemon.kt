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
// FUNCION PARA BORRAR UN DOCUMENTO DE LA COLECCION
fun BorrarCliente(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "clientes"
    var id by remember { mutableStateOf("") }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
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
                text = "Eliminar cliente",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("Introduce el NIF a borrar") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            var mensaje_borrado by remember { mutableStateOf("") }

            Button(

                onClick = {
                    if (id.isNotBlank()) {
                        db.collection(nombre_coleccion)
                            .document(id)
                            .delete()
                            .addOnSuccessListener {
                                mensaje_borrado = "Datos borrados correctamente"
                                id = ""
                            }
                            .addOnFailureListener {
                                mensaje_borrado = "No se ha podido borrar"
                                id = ""
                            }
                    }
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
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = mensaje_borrado)
        }

    }

}