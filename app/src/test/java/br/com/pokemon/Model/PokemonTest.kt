package br.com.pokemon.Model

import junit.framework.TestCase

class PokemonTest : TestCase() {

    //IREMOS REALIZAR UM TESTE SIMPLES NO METODO SETTAR O ID DO POKEMON
    fun testSetId() {
        //CRIAMOS O CENARIO DE TESTE
        var pokemon = Pokemon()
        pokemon.id = 5

        //EXECUTAMOS NOSSA ACAO ESPERADA PARA VISUALIZARMOS O ID
        var idPokemon = pokemon.id

        //POR ULTIMO REALIZAMOS O NOSSO TESTE
        assertEquals(5, idPokemon)
    }
}