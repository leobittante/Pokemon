package br.com.pokemon.View.Fragments.Adapter

import android.view.View

//INTERFACE PARA CAPTURAR O CLIQUE EM UM POKEMONS ESPECIFICO
interface ClickListenerAdapter {
    fun OnClickListener(view: View, position: Int)
}