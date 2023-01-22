package com.example.movcomp_eepr

import android.os.Parcel
import android.os.Parcelable

class BEntrenador(
    var id:Int,
    var nombre:String,
    var descripcion:String
//Para cualquiera pueda entender los datos
):Parcelable{
    //Leer

    constructor(parcel: Parcel): this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        //parcel.readParcelable<>()
    ){}

    override fun toString(): String {
        return "${id}.${nombre} - ${descripcion}"
    }

    //Escribir
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }


}

/*{
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}
*/


