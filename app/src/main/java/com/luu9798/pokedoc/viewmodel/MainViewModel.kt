package com.luu9798.pokedoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luu9798.pokedoc.model.PokemonLink
import com.luu9798.pokedoc.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemonLinks = MutableLiveData<List<PokemonLink>>()
    val pokemonLink: LiveData<List<PokemonLink>> = _pokemonLinks

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var offsetQuery: Int = 0

    suspend fun updatePokemonLinks(newLinks: List<PokemonLink>) {
        _pokemonLinks.postValue(newLinks)
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun areThereMorePokemon(): Boolean {
        return offsetQuery < MAX_OFFSET_QUERY
    }

    fun increaseOffset() {
        if (areThereMorePokemon()) {
            offsetQuery += LIMIT_QUERY
        }
    }

    fun fetchPokemonLinks() {
        setLoading(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pokemonPage = pokemonRepository.getPokemonList(offset = offsetQuery, limit = LIMIT_QUERY)
                updatePokemonLinks(pokemonPage.results)
            }
        }
    }

    companion object {
        private const val LIMIT_QUERY = 20
        private const val MAX_OFFSET_QUERY = 600
    }
}
