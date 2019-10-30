package es.iessaladillo.pedrojoya.profile.data.local.entity

import android.os.Parcel
import android.os.Parcelable


class Avatar() : Parcelable {

    var id: Int = 0
    var imageResId: Int = 0
    var name: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        imageResId = parcel.readInt()
        name = parcel.readString()
    }

    constructor(id: Int, imageResId: Int, name: String) : this() {
        this.id = id
        this.imageResId = imageResId
        this.name = name
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(imageResId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Avatar> {
        override fun createFromParcel(parcel: Parcel): Avatar {
            return Avatar(parcel)
        }

        override fun newArray(size: Int): Array<Avatar?> {
            return arrayOfNulls(size)
        }
    }

}
