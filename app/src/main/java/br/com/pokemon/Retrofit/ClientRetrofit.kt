package br.com.pokemon.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//OBJETO DE CONEXAO RETROFIT
object ClientRetrofit {

    //CRIAMOS A VARIAVEL DO TIPO "RETROFIT"
    private var retrofit:Retrofit? = null
    val instance: Retrofit

    get() {
        if(retrofit == null)
        //DEFINIMOS O BUILDER DA NOSSA REQUISICAO
        retrofit = Retrofit.Builder()
            //DEFINIMOS NOSSA URL BASE
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")

            //CONVERTMOS OS DADOS RECEBIDOS EM JSON COM A BIBLIOTECA GSON
            .addConverterFactory(GsonConverterFactory.create())

            //DEFINIMOS NOSSO ADAPTADOR RX ONDE NOS POSSIBILITAR RETORNARMOS TIPOS "OBSERVABLES, FLOWABLE, ETC"
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            //CRIAMOS O BUILD
            .build()
        return retrofit!!
    }
}