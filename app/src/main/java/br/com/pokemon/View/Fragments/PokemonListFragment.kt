package br.com.pokemon.View.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.pokemon.R
import br.com.pokemon.ViewModel.PokemonListFragmentViewModel

class PokemonListFragment : Fragment() {

    private lateinit var pokemonListFragmentViewModel: PokemonListFragmentViewModel

    //METODO EXECUTADO AO CRIARMOS NOSSO FRAGMENTO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        //INSTANCIAMOS NOSSA CLASSE VIEWMODEL
        pokemonListFragmentViewModel = PokemonListFragmentViewModel(requireActivity())

        //CHAMAMOS O METODO PARA LISTAR NOSSOS POKEMONS
        pokemonListFragmentViewModel.listarPokemonsAPI()

        return view
    }
}