package br.com.pokemon.View.Fragments.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pokemon.R
import br.com.pokemon.Model.Pokemon
import com.bumptech.glide.Glide

//CLASSE ADAPTER PARA NOSSO RECYCLER VIEW
class PokemonListAdapter (internal val contex:Context,
                          internal var listPokemon:List<Pokemon>):
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    //INICIALIZAR OS COMPONENTES
    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item) {

          //CRIA AS VARIAVEIS DE ACORDO COM OS COMPONENTES
          internal var imgPokemon:ImageView
          internal var tvPokemon:TextView
          internal var clickListenerAdapter: ClickListenerAdapter? = null

        fun setClickListener(clickListenerAdapter: ClickListenerAdapter){
            this.clickListenerAdapter = clickListenerAdapter
        }

          //INICIALIZA NOSSAS VARIAVEIS ATRIBUINDO-AS NOS COMPONENTES
          init {
              imgPokemon = item.findViewById(R.id.imgPokemon) as ImageView
              tvPokemon = item.findViewById(R.id.tvPokemon) as TextView

              //REOTRNA A POSICAO NO RECYCLER CLICADO PELO USUARIO
              item.setOnClickListener { view -> clickListenerAdapter!!.OnClickListener(view, adapterPosition) }
          }
      }

    //METODO EXECUTADO AO CRIAR NOSSO ADAPTER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //AQUI DEFINIMOS QUAL LAYOUT O RECYvCLER VIEW IRA EXIBIR
        val item:View = LayoutInflater.from(contex).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(item)
    }

    /*POR ULTIMO, RECEBEMOS A POSICAO ATUAL DA LISTA E ATRELAMOS OS DADOS (FOTO E NOME)
    NOS HOLDERS. UTILIZAMOS A BIBLIOTECA GLIDE PARA CARREGAR AS IMAGENS*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(contex).load(listPokemon[position].img).into(holder.imgPokemon)
        holder.tvPokemon.text = listPokemon[position].name

        //EVENTO AO CLICAR EM UM POKEMON EM ESPECIFICO
        holder.setClickListener(object: ClickListenerAdapter{
            override fun OnClickListener(view: View, position: Int) {
                //AO CLICAR NO POKEMON IREMOS ENCAMINHAR OS VALORES DELE PARA A PROXIMA ACTIVITY
                //ATRAVAES DA BIBLIOTECA PARCELABLE
                LocalBroadcastManager.getInstance(contex)
                    .sendBroadcast(Intent("pokemon").putExtra("pokemon", listPokemon[position]))
            }
        })
    }

    //METODO PARA DEFINIRMOS O TAMANHO DO RECYCLER VIEW
    //IREMOS DEFINIR IGUAL AO TAMANHO DA NOSSA LISTA DE POKEMONS
    override fun getItemCount(): Int {
        return listPokemon.size
    }


}