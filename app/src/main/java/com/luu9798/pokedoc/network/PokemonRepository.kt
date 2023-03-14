package com.luu9798.pokedoc.network

import com.luu9798.pokedoc.model.PokemonPage
import javax.inject.Inject

class PokemonRepository(val pokemonService: PokemonRetrofitService) {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonPage {
        return pokemonService.getPokemonPage(offset, limit)
    }
}
