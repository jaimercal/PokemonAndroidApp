package com.example.pokemonandroidapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonandroidapp.model.Pokemon
import com.google.firebase.firestore.FirebaseFirestore

class ListPokemonViewModel:ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val collectionName = "pokemon"

    private val _pokemon = MutableLiveData<List<Pokemon>>()
    val pokemon: LiveData<List<Pokemon>> = _pokemon

    fun loadPokemon(){
        val pokemon: MutableList<Pokemon> = mutableListOf<Pokemon>()
        db.collection(collectionName)
            .get()
            .addOnSuccessListener {
                pokemon.clear()
                for (received in it) {
                    val auxPokemon = Pokemon(received.id, received.get("name") as String, received.get("primaryType") as String, received.get("secondaryType") as String)
                    pokemon.add(auxPokemon)
                }
                _pokemon.value = pokemon
            }
    }

    fun deletePokemon(pokemonNumber: String){
        db.collection(collectionName)
            .document(pokemonNumber)
            .delete()
    }
}