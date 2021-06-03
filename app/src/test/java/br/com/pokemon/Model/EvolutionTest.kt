package br.com.pokemon.Model

import junit.framework.TestCase

class EvolutionTest : TestCase() {

    //TESTE DE EXEMPLO NA CLASSE EVOLUTION
    //TESTAMOS O METODO GET PARA RETORNAR O NOME DA EVOLUCAO
    fun testTestGetName() {
        //PRIMEIRO CRIAMOS NOSSO CENARIO
        var evolution = Evolution()
        evolution.name = "Teste Pokemon"

        //DEPOIS EXECUTAMOS A ACAO ESPERADA
        var resultado = evolution.name

        //POR ULTIMO TESTAMOS NOSSO RESULTADO ESPERADO
        assertEquals("Teste Pokemon", resultado)

        //TESTE APROVADO SEM ERROS
    }
}