package com.luu9798.pokedoc.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): PokemonRetrofitService {
        return PokemonRetrofitService(retrofit)
    }

    @Provides
    fun provideRepository(service: PokemonRetrofitService): PokemonRepository {
        return PokemonRepository(service)
    }
}
