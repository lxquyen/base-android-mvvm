package com.lxquyen.sample.data.source.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey var id: String = "",
    var email: String = "",
    var username: String = "",
    var password: String = "",
    var fullName: String = "",
    var avatar: String = ""
) {
    @Ignore
    constructor() : this("")
}