package br.com.pokemon.View

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.pokemon.Model.Pokemon
import br.com.pokemon.R
import br.com.pokemon.View.Fragments.DetailPokemonFragment

//PRIMEIRA CLASSE INICIADA AO SER EXECUTADO O APP
class MainActivity : AppCompatActivity() {

    private val showDetail = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            //VALIDAMOS SE A INFORMACAO ENTRE AS ACTIVITYS SAO RECEBIDAS E NAO SAO NULAS
                if(intent?.action!!.toString() == "pokemon"){
                    //INSTANCIAMOS NOSSO FRAGMENTO
                    val detailPokemonFragment = DetailPokemonFragment.getInstance()

                    //RECOLHEMOS NOSSA LISTA "POKEMON" ENVIADAS PELO ADAPTER E ALOCAMOS NA VARIAVEL
                    var pokemon = intent.getParcelableExtra<Pokemon>("pokemon")

                    //CRIAMOS NOSSO BUNDLE PARA ENVIARMOS AS INFORMACOES PARA NOSSO FRAGMENTO
                    val bundle = Bundle()
                    bundle.putParcelable("pokemon", pokemon)
                    detailPokemonFragment.arguments = bundle

                    //APOS, CHAMAMOS NOSSO PROXIMO FRAGMENTO - DETALHES DO POKEMON
                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.PokemonListFragment, detailPokemonFragment)
                    fragmentTransaction.addToBackStack("detail")
                    fragmentTransaction.commit()
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //REGISTRAMOS O BROADCAST PARA FUNCIONAR
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter("pokemon"))
    }
}