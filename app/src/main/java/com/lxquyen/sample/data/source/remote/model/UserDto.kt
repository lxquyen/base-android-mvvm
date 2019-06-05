package com.lxquyen.sample.data.source.remote.model

import com.google.gson.annotations.SerializedName

class UserDto(val id: String?,
              val email: String?,
              val username: String?,
              val password: String?,
              @SerializedName("fullName") val fullName: String?,
              val avatar: String?)