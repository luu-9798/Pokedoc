package com.luu9798.pokedoc.network

import com.luu9798.pokedoc.model.PokemonPage
import retrofit2.Retrofit
import retrofit2.create

class PokemonRetrofitService(retrofit: Retrofit): PokemonRetrofitInterface {
    private val pokemonRetrofit = retrofit.create(PokemonRetrofitInterface::class.java)

    override suspend fun getPokemonPage(offset: Int, limit: Int): PokemonPage {
        return pokemonRetrofit.getPokemonPage(
            offset = offset,
            limit = limit
        )
    }
}