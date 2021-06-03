package br.com.pokemon.Model

import android.os.Parcel
import android.os.Parcelable

//MODEL: EVOLUTION
//IMPLEMENTAMOS A CLASSE PARCELABLE PARA PASSARMOS OS DADOS PARCELADOS ENTRE ACTIVITYS
class Evolution() : Parcelable{

    var num:String? = null
    var name:String? = null

    constructor(parcel: Parcel) : this() {
        num = parcel.readString()
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(num)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Evolution> {
        override fun createFromParcel(parcel: Parcel): Evolution {
            return Evolution(parcel)
        }

        override fun newArray(size: Int): Array<Evolution?> {
            return arrayOfNulls(size)
        }
    }
}