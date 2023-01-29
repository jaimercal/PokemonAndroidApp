package com.example.pokemonandroidapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavePokemonViewModel:ViewModel() {
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
}