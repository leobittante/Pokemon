package br.com.pokemon.View.Fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pokemon.Alerts.AlertDialogPersonalizado
import br.com.pokemon.Model.Pokemon
import br.com.pokemon.R
import br.com.pokemon.View.Fragments.AdapterDetails.PokemonWeaknessesListAdapter
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream

class DetailPokemonFragment : Fragment() {

    //DECLARAMOS NOSSAS VARIAVEIS - COMPONENTES DE TELA
    internal lateinit var pkName:TextView
    internal lateinit var pkHeight:TextView
    internal lateinit var pkWeight:TextView
    internal lateinit var imgPokemonDetail:ImageView
    internal lateinit var rvType:RecyclerView
    internal lateinit var rvWeaknesses:RecyclerView
    internal lateinit var rvNextEvolution:RecyclerView
    internal lateinit var imgShared:ImageView
    //NOSSO ARRAY DE STRINGS PARA SOLICITARMOS PERMISSOES
    private val PERMISSIONS = arrayOf(
        "android.permission.WRITE_EXTERNAL_STORAGE",
    )

    //CRIAMOS NOSSA INSTANCIA DA CLASSE
    companion object{
        internal var instance:DetailPokemonFragment?=null

        fun getInstance():DetailPokemonFragment{
            if(instance == null)
                instance = DetailPokemonFragment()
            return instance!!
        }
    }

    //METODO CHAMADO AO CRIAR NOSSO FRAGMENTO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val item = inflater.inflate(R.layout.fragment_detail_pokemon, container, false)

        //INSTANCIAR NOSSOS COMPONENTES DE TELA
        pkName = item.findViewById(R.id.pkName)
        pkHeight = item.findViewById(R.id.pkHeight)
        pkWeight = item.findViewById(R.id.pkWeight)
        imgPokemonDetail = item.findViewById(R.id.imgPokemonDetail)
        rvType = item.findViewById(R.id.rvType)
        rvWeaknesses = item.findViewById(R.id.rvWeaknesses) as RecyclerView
        rvNextEvolution = item.findViewById(R.id.rvNextEvolution)
        imgShared = item.findViewById(R.id.imgShared)

        //COLETAMOS NOSSO VALOR PASSADO PELA MAIN ACTIVITY E ATRELAMOS A NOSSA VARIAVEL POKEMON
        var pokemon = arguments?.getParcelable<Pokemon>("pokemon")

        createRecycler(rvType)
        createRecycler(rvWeaknesses)
        createRecycler(rvNextEvolution)

        //DEFINIMOS NOSSO ADAPTER CONFORME A LISTA RECEBIDA DE POKEMONS
        if(pokemon?.type != null) rvType.adapter = PokemonWeaknessesListAdapter(this.requireContext(), pokemon.type!!, null)

        //DEFINIMOS NOSSO ADAPTER CONFORME A LISTA RECEBIDA DE POKEMONS
        if(pokemon?.weaknesses != null) rvWeaknesses.adapter = PokemonWeaknessesListAdapter(this.requireContext(), pokemon.weaknesses!!, null)

        //DEFINIMOS NOSSO ADAPTER CONFORME A LISTA RECEBIDA DE POKEMONS
        if(pokemon?.next_evolution != null) rvNextEvolution.adapter = PokemonWeaknessesListAdapter(this.requireContext(), null, pokemon.next_evolution!!)


        //SETTAR NOSSOS COMPONENTES COM OS DADOS RECOLHIDOS DOS POKEMONS
        Glide.with(item).load(pokemon?.img).into(imgPokemonDetail)
        pkName.setText(pokemon?.name)
        pkHeight.setText(pkHeight.text.toString() + pokemon?.height)
        pkWeight.setText(pkWeight.text.toString() + pokemon?.weight)

        //EVENTO AO CLICAR NO BOTAO SHARED
        imgShared.setOnClickListener { View ->

            /*PARA UTILIZARMOS O SISTEMA DE COMPARTILHAMENTO DE DADOS
            PRECISAMOS ACESSAR O ARMAZENAMENTO INTERNO DO USUARIO. ANTES DE CONTINUARMOS
            IREMOS CHECAR SE FOI CONCEDIDA A PERMISSAO DE ARMAZENAMENTO INTERNO */
            if (!checkPermission()) {
                var alertDialogPersonalizado = AlertDialogPersonalizado()
                alertDialogPersonalizado.show(
                    R.string.permission_title,
                    R.string.permission_message,
                    requireContext()
                )
                requestPermission()
            }

            //CASO A PERMISSAO FOI CONCEDIDA, CONTINUAREMOS O COMPARTILHAMENTO
            else {

                //CRIAMOS UMA INTENÇAO COM AÇAO DE ENVIO
                var intent = Intent(Intent.ACTION_SEND)
                //SETAMOS O TIPO DE ENVIO: NO CASO SERIA TODOS, MIDIA, TEXTO, ETC
                intent.setType("*/*")
                //CRIAMOS UM BITMAPDRAWABLE E ADICIONAMOS NOSSA IMAGEM DO IMAGEVIEW
                var drawable: BitmapDrawable = imgPokemonDetail.drawable as BitmapDrawable
                /*CRIAMOS UM BITMAP E COLETAMOS DO NOSSO DRAWABLE UMA IMAGEM COM QUALIDADE INFERIOR PARA
                EVITAR VAZAMENTO DE MEMORIA QUANDO FORMOS COMPARTILHAR A IMAGEM*/
                var bitmap: Bitmap = drawable.bitmap
                /*A VARIAVEL BYTES E UTILIZADA PARA AUXILIAR NA COMPRESSAO DA NOSSA IMAGEM
                PARA REDUZIR SUA QUALIDADE*/
                var bytes = ByteArrayOutputStream()
                /*UTILIZAMOS O TIPO DE COMPRESSAO "PNG" E PEGAMOS 100% DE QUALIDADE DA NOSSA IMAGEM JA COMPRIMIDA
                NAO FAZ SENTIDO PEGARMOS MENOS QUALIDADE POIS A IMAGEM JA ESTA COMPRESSIMIDA, OU SEJA,
                UMA QUALIDADE INFERIOR*/
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
                //CRIAMOS UM CAMINHO PARA NOSSA NOVA IMAGEM
                var path: String = MediaStore.Images.Media.insertImage(
                    requireContext().contentResolver,
                    bitmap,
                    null,
                    null
                )
                /*A VARIAVEL DO TIPO "URI" E UM PADRAO PARA IDENTIFICAR DOCUMENTOS, IMAGENS, E MUITOS OUTROS, USANDO
                UMA SEQUENCIA DE NUMEROS, LETRAS E SIMBOLOS. NO NOSSO CASO ELA ESTA ARMAZENANDO AS INFORMACOES DA
                NOSSA IMAGEM PARA PODER SER COMPARTILHADA POR OUTRA APLICACAO*/
                var uri: Uri = Uri.parse(path)
                //ESTAMOS PASSANDO INFORMACOES EXTRAS PARA NOSSA NOVA INTENT, NESTE CASO NOSSA IMAGEM
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                //OUTRA INFORMACAO EXTRA SENDO A DESCRICAO QUE ENVIAREMOS JUNTO COM A IMAGEM
                //AQUI PASSAMOS OS DADOS SIMPLES DO POKEMON SELECIONADO
                intent.putExtra(
                    Intent.EXTRA_TEXT, "O Pokemon " + pokemon?.name + " possui "
                            + pokemon?.height + " de altura e pesa " + pokemon?.weight + ". Seu tipo é "
                            + pokemon?.type?.get(0) + " e sua fraqueza é " + pokemon?.weaknesses?.get(
                        0
                    )
                )
                //POR ULTIMO INICIAMOS NOSSA ACTIVITY E PASSAMOS UM TITULO DE COMPARTILHAMENTO,
                //NESSE CASO O NOME DO POKEMON
                startActivity(Intent.createChooser(intent, "Compartilhar " + pokemon?.name))
            }
        }

        return item
    }

    //CHECAR SE A PERMISSAO DE LOCALIZACAO ESTA ATIVA, CASO CONTRARIO RETORNA UMA MENSAGEM E VOLTA PARA O LOGIN
    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    //METODO PARA SOLICITARMOS AS PERMISSOES DE ARMAZENAMENTO
    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS, 200)
    }

    //CRIAR NOSSOS RECYCLERS
   private fun createRecycler(recyclerView: RecyclerView){
        //CRIA NOSSO RECYCLER VIEW - TIPO
       recyclerView.setHasFixedSize(true)
        //ATIVAMOS A VISIBILIDADE
       recyclerView.visibility
        //ADICIONAMOS UM LAYOUT
       recyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}