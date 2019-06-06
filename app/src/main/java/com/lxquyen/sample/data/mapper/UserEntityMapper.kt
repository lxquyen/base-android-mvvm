package com.lxquyen.sample.data.mapper

import com.lxquyen.sample.data.mapper.base.Mapper
import com.lxquyen.sample.data.source.local.model.UserEntity
import com.lxquyen.sample.domain.model.User
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : Mapper<UserEntity, User>() {
    override fun map(from: UserEntity): User {
        return from.let {
            User(
                id = it.id,
                email = it.email,
                username = it.username,
                password = it.password,
                fullName = it.fullName,
                avatar = it.avatar
            )
        }
    }

    override fun reverse(to: User): UserEntity {
        return to.let {
            UserEntity().apply {
                id = it.id ?: ""
                email = it.email ?: ""
                username = it.username ?: ""
                password = it.password ?: ""
                fullName = it.fullName ?: ""
                avatar = it.avatar ?: ""
            }
        }
    }

}