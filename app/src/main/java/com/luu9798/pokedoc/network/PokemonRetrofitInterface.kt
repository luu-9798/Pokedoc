package com.luu9798.pokedoc.network

import com.luu9798.pokedoc.model.PokemonPage
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonRetrofitInterface {
    @GET("pokemon")
    suspend fun getPokemonPage(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonPage
}