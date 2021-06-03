package br.com.pokemon.Retrofit

import br.com.pokemon.Model.Pokedex
import retrofit2.Call
import retrofit2.http.GET

//INTERFACE PARA ARMAZENAR NOSSOS "ENDPOINTS"
//PADRAO: RETROFIT
interface iListPokemons {

    //REQUISICAO "GET" PARA RETORNAR OS VALORES NO ENDPOINT ESPECIFICADO
    //UTLIZAREMOS NOSSO MODEL "POKEDEX" PARA RECEBER OS DADOS
    @get:GET("pokedex.json")
    val listPokemon: Call<Pokedex>
}