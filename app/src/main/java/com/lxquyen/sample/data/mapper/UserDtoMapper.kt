package com.lxquyen.sample.data.mapper

import com.lxquyen.sample.data.mapper.base.Mapper
import com.lxquyen.sample.data.source.remote.model.UserDto
import com.lxquyen.sample.domain.model.User
import javax.inject.Inject

class UserDtoMapper @Inject constructor() : Mapper<UserDto, User>() {
    override fun map(from: UserDto): User {
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

    override fun reverse(to: User): UserDto {
        return to.let {
            UserDto(
                id = it.id,
                email = it.email,
                username = it.username,
                password = it.password,
                fullName = it.fullName,
                avatar = it.avatar
            )
        }
    }
}