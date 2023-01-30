package com.example.pokemonandroidapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class SavePokemonViewModel:ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val collectionName = "pokemon"

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage : LiveData<String> = _responseMessage

    private val _successMessage = "Datos guardados correctamente"
    private val _errorMessage = "No se ha podido guardar"

    private val _pokemonNumber = MutableLiveData<String>()
    val pokemonNumber : LiveData<String> = _pokemonNumber

    private val _pokemonName = MutableLiveData<String>()
    val pokemonName : LiveData<String> = _pokemonName

    private val _pokemonPrimaryType = MutableLiveData<String>()
    val pokemonPrimaryType : LiveData<String> = _pokemonPrimaryType

    private val _pokemonSecondaryType = MutableLiveData<String>()
    val pokemonSecondaryType : LiveData<String> = _pokemonSecondaryType

    private val _isButtonEnable =MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    val pokemonPrimaryTypes = listOf(
        "Normal", "Fuego", "Agua", "Eléctrico", "Planta", "Hielo", "Lucha", "Veneno", "Tierra", "Volador", "Psíquico", "Bicho", "Roca", "Fantasma", "Dragón", "Siniestro", "Acero", "Hada"
    )
    val pokemonSecondaryTypes = listOf(
        "Normal", "Fuego", "Agua", "Eléctrico", "Planta", "Hielo", "Lucha", "Veneno", "Tierra", "Volador", "Psíquico", "Bicho", "Roca", "Fantasma", "Dragón", "Siniestro", "Acero", "Hada", "No tiene"
    )

    fun onCompletedFields(pokemonNumber:String, pokemonName:String, pokemonPrimaryType:String, pokemonSecondaryType:String) {
        _pokemonNumber.value = pokemonNumber
        _pokemonName.value = pokemonName
        _pokemonPrimaryType.value = pokemonPrimaryType
        _pokemonSecondaryType.value = pokemonSecondaryType
        _isButtonEnable.value = enableButton(pokemonNumber, pokemonName, pokemonPrimaryType, pokemonSecondaryType)
    }

    fun enableButton(pokemonNumber:String, pokemonName:String, pokemonPrimaryType:String, pokemonSecondaryType:String) =
        //Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        pokemonNumber.length>0 && pokemonName.length>0 && pokemonPrimaryType.length>0 && pokemonSecondaryType.length>0

    private fun convertToData(pokemonNumber:String, pokemonName:String, pokemonPrimaryType:String, pokemonSecondaryType:String): HashMap<String, String?>{
        return hashMapOf(
            "number" to pokemonNumber.toString(),
            "name" to pokemonName.toString().lowercase(),
            "primaryType" to pokemonPrimaryType.toString(),
            "secondaryType" to pokemonSecondaryType.toString()
        )
    }

    fun addPokemon(
        pokemonNumber:String,
        pokemonName:String,
        pokemonPrimaryType:String,
        pokemonSecondaryType:String,
    ){
        val data = convertToData(pokemonNumber, pokemonName, pokemonPrimaryType, pokemonSecondaryType)

        db.collection(collectionName)
            .document(pokemonNumber)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                _responseMessage.value = _successMessage
            }
            .addOnFailureListener {
                _responseMessage.value = _errorMessage
            }
            .addOnCompleteListener{
                _pokemonNumber.value = ""
                _pokemonName.value = ""
                _pokemonPrimaryType.value = ""
                _pokemonSecondaryType.value = ""
            }
    }
}