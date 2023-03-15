package com.luu9798.pokedoc.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.luu9798.pokedoc.databinding.FragmentHomeBinding
import com.luu9798.pokedoc.view.adapter.PokemonLinkAdapter
import com.luu9798.pokedoc.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var pokemonLinkAdapter: PokemonLinkAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewPokemonLink.adapter = pokemonLinkAdapter
        binding.recyclerViewPokemonLink.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && viewModel.areThereMorePokemon()) {
                    viewModel.increaseOffset()
                    viewModel.fetchPokemonLinks()
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewPokemonLink.visibility = View.VISIBLE
            }
        }

        viewModel.pokemonLink.observe(viewLifecycleOwner) {
            viewModel.setLoading(false)
            pokemonLinkAdapter.pokemonLinks.addAll(it)
            pokemonLinkAdapter.notifyDataSetChanged()
        }

        viewModel.fetchPokemonLinks()
    }
}
