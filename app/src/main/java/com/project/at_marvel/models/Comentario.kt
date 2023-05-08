package com.project.at_marvel.models

import android.os.Parcelable
import com.project.at_marvel.help.FirebaseHelper
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comentario(
    var id: String = "",
    var filme: String = "",
    var avaliacao: String =""
) : Parcelable{
    init {
        this.id = FirebaseHelper.getDatabase().push().key ?: ""
    }
}
