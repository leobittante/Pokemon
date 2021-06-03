package br.com.pokemon.View.Fragments.AdapterDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.pokemon.Model.Evolution
import br.com.pokemon.R

//PARA REAPROVEITAR A CLASSE UTILIZEI DUAS CHAMADAS DE RECYCLER NO MESMO ADAPTER
//SE UM FOR NULO RETORNAMOS O OUTRO
class PokemonWeaknessesListAdapter(internal val contex: Context,
                                   internal var listEvolution: List<String>?,
                                   internal var listEvolution2: List<Evolution>?
):
    RecyclerView.Adapter<PokemonWeaknessesListAdapter.ViewHolder>() {

    //INICIALIZAR OS COMPONENTES
    inner class ViewHolder(item: View):RecyclerView.ViewHolder(item) {

        //CRIA AS VARIAVEIS DE ACORDO COM OS COMPONENTES
        internal var txtDetail: TextView

        //INICIALIZA NOSSAS VARIAVEIS ATRIBUINDO-AS NOS COMPONENTES
        init {
            txtDetail = item.findViewById(R.id.txtDetail) as TextView
        }
    }

    //METODO EXECUTADO AO CRIAR NOSSO ADAPTER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonWeaknessesListAdapter.ViewHolder {
        //AQUI DEFINIMOS QUAL LAYOUT O RECYvCLER VIEW IRA EXIBIR
        val item: View = LayoutInflater.from(contex).inflate(R.layout.text_details, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: PokemonWeaknessesListAdapter.ViewHolder, position: Int) {
        if(listEvolution == null) holder.txtDetail.setText(listEvolution2?.get(position)?.name)
        else holder.txtDetail.setText(listEvolution?.get(position))
    }

    //EXISTEM DOIS RECYCLERS NO MESMO ADAPTER, SE UM FOR NULO A GENTE RETORNA O OUTRO PARA CONTINUAR A LISTAGEM
    override fun getItemCount(): Int {
        if(listEvolution == null) return listEvolution2!!.size
        else return listEvolution!!.size
    }
}