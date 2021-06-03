package br.com.pokemon.Model

import android.os.Parcel
import android.os.Parcelable

//MODEL: POKEMON
//CLASSE ONDE ARMAZENARA OS DADOS DO POKEMOM
//IMPLEMENTAMOS A CLASSE PARCELABLE PARA PASSARMOS OS DADOS PARCELADOS ENTRE ACTIVITYS
class Pokemon() : Parcelable{

    //DADOS DO POKEMON
    var id:Int = 0
    var num: String? = null
    var name: String? = null
    var img: String? = null
    var type: List<String>? = null
    var height: String? = null
    var weight: String? = null
    var candy: String? = null
    var candy_count:Int = 0
    var egg: String? = null
    var spawn_chance:Double = 0.toDouble();
    var avg_spawns:Double = 0.toDouble();
    var spawn_time: String? = null
    var multipliers: List<Double>? = null
    var weaknesses: List<String>? = null

    //LISTAS DE EVOLUCOES
    //CRIA UMA LISTA DE CLASSES "EVOLUTION"
    var next_evolution: List<Evolution>? = null
    var prev_evolution: List<Evolution>? = null

    //IMPLEMENTAMOS A BIBLIOTECA PARCELABLE PARA ENVIAR VALORES ENTRE AS ACTIVITYS
    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        num = parcel.readString()
        name = parcel.readString()
        img = parcel.readString()
        type = parcel.createStringArrayList()
        height = parcel.readString()
        weight = parcel.readString()
        candy = parcel.readString()
        candy_count = parcel.readInt()
        egg = parcel.readString()
        spawn_chance = parcel.readDouble()
        avg_spawns = parcel.readDouble()
        spawn_time = parcel.readString()
        weaknesses = parcel.createStringArrayList()
        next_evolution = parcel.createTypedArrayList(Evolution)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(num)
        parcel.writeString(name)
        parcel.writeString(img)
        parcel.writeStringList(type)
        parcel.writeString(height)
        parcel.writeString(weight)
        parcel.writeString(candy)
        parcel.writeInt(candy_count)
        parcel.writeString(egg)
        parcel.writeDouble(spawn_chance)
        parcel.writeDouble(avg_spawns)
        parcel.writeString(spawn_time)
        parcel.writeStringList(weaknesses)
        parcel.writeList(next_evolution)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }

}