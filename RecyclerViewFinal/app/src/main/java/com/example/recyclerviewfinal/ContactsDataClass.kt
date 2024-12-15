package com.example.recyclerviewfinal

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Contact (
    var contactName: String,
    var contactSurname: String,
    var contactPhone: String,
    val contactPhoto: String

) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )


    override fun describeContents(): Int {
return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contactName)
        parcel.writeString(contactSurname)
        parcel.writeString(contactPhone)
        parcel.writeString(contactPhoto)
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}