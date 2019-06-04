package com.lxquyen.sample.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String? = null,
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var fullName: String? = null,
    var avatar: String? = null
) : Parcelable {
    override fun toString(): String {
        return "id: $id" +
                "\nemail: $email" +
                "\nusername: $username" +
                "\npassword: $password" +
                "\nfullName: $fullName" +
                "\navatar: $avatar"
    }
}