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

fun GuardarCliente(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "clientes"
    var id by remember { mutableStateOf("") }
    var nombre_cliente by remember { mutableStateOf("") }
    var direccion_cliente by remember { mutableStateOf("") }
    var telefono_cliente by remember { mutableStateOf("") }
    var mail_cliente by remember { mutableStateOf("") }

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
                text = "Guardar cliente",
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("Introduce el NIF") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = nombre_cliente,
                onValueChange = { nombre_cliente = it },
                label = { Text("Introduce el nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))

            OutlinedTextField(
                value = direccion_cliente,

                onValueChange = { direccion_cliente = it },
                label = { Text("Introduce la dirección") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))



            OutlinedTextField(
                value = mail_cliente,
                onValueChange = { mail_cliente = it },
                label = { Text("Introduce el mail") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = telefono_cliente,
                onValueChange = { telefono_cliente = it },
                label = { Text("Introduce el teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.size(5.dp))
            val dato = hashMapOf(
                "nif" to id.toString(),
                "nombre" to nombre_cliente.toString(),
                "direccion" to direccion_cliente.toString(),
                "mail" to mail_cliente.toString(),
                "telefono" to telefono_cliente.toString()
            )

            var mensaje_confirmacion by remember { mutableStateOf("") }

            Button(

                onClick = {
                    db.collection(nombre_coleccion)
                        .document(id)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje_confirmacion ="Datos guardados correctamente"
                            id =""
                            nombre_cliente=""
                            direccion_cliente=""
                            mail_cliente=""
                            telefono_cliente=""
                        }
                        .addOnFailureListener {
                            mensaje_confirmacion ="No se ha podido guardar"
                            id =""
                            nombre_cliente=""
                            direccion_cliente=""
                            mail_cliente=""
                            telefono_cliente=""
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
            Text(text = mensaje_confirmacion)
        }

    }
}