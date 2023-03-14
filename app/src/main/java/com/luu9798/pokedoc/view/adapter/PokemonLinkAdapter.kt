package com.luu9798.pokedoc.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luu9798.pokedoc.databinding.ViewHolderPokemonLinkBinding
import com.luu9798.pokedoc.model.PokemonLink

class PokemonLinkAdapter(val pokemonLinks: ArrayList<PokemonLink>):
    RecyclerView.Adapter<PokemonLinkAdapter.ViewHolder>() {

    class ViewHolder(binding: ViewHolderPokemonLinkBinding) : RecyclerView.ViewHolder(binding.root) {
        val pokemonLinkTextView: TextView = binding.textViewPokemonLink
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderPokemonLinkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pokemonLinkTextView.text = pokemonLinks[position].name
    }

    override fun getItemCount(): Int = pokemonLinks.size
}
