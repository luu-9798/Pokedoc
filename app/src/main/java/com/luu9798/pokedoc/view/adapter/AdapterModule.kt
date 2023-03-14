package com.luu9798.pokedoc.view.adapter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {

    @Provides
    fun providePokemonLinkAdapter(): PokemonLinkAdapter {
        return PokemonLinkAdapter(ArrayList())
    }
}
