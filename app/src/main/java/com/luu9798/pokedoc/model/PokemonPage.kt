package com.luu9798.pokedoc.model

data class PokemonPage (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonLink>
)
