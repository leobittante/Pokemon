package br.com.pokemon.ViewModel

import android.app.Activity
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pokemon.Model.Pokedex
import br.com.pokemon.R
import br.com.pokemon.Retrofit.ClientRetrofit
import br.com.pokemon.Retrofit.iListPokemons
import br.com.pokemon.View.Fragments.Adapter.PokemonListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListFragmentViewModel (private var activity: Activity){

    private var listPokemons: iListPokemons
    private lateinit var recyclerPokemon:RecyclerView

    //INSTANCIAMOS NOSSA CLASSE RETROFIT PARA COLETARMOS OS DADOS DO NOSSO ENDPOINT
    init {
        val retrofit = ClientRetrofit.instance
        listPokemons = retrofit.create(iListPokemons::class.java)
    }

    fun listarPokemonsAPI(){
        //RETORNAMOS O CALLBACK (CHAMADA DE RETORNO) DO NOSSO RETROFIT E VERIFICAMOS SUCESSO OU FALHA
        //RETORNO DO TIPO ASSINCRONO PARA NAO TRAVAR NOSSA UI PRINCIPAL
        listPokemons.listPokemon.enqueue(object : Callback<Pokedex> {
            //RETORNADO COM SUCESSO
            override fun onResponse(call: Call<Pokedex>, response: Response<Pokedex>) {

                //CRIA NOSSO RECYCLER VIEW
                recyclerPokemon = activity.findViewById(R.id.RecyclerPokemon) as RecyclerView
                recyclerPokemon.setHasFixedSize(true)
                recyclerPokemon.visibility
                //ADICIONAMOS UM LAYOUT
                recyclerPokemon.layoutManager = GridLayoutManager(activity, 3)
                //DEFINIMOS NOSSO ADAPTER CONFORME A LISTA RECEBIDA DE POKEMONS
                recyclerPokemon.adapter = PokemonListAdapter(activity, response.body()?.pokemon!!)

            }
            //RETORNADO COM FALHA
            override fun onFailure(call: Call<Pokedex>, t: Throwable) {
                //EXIBIMOS UMA MENSAGEM DE ERRO
                Toast.makeText(activity,R.string.error_list_pokemon, Toast.LENGTH_LONG).show()
            }
        })
    }

}