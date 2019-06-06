package com.lxquyen.sample.common.di.module

import com.lxquyen.sample.data.mapper.UserDtoMapper
import com.lxquyen.sample.data.mapper.UserEntityMapper
import com.lxquyen.sample.data.mapper.base.Mapper
import com.lxquyen.sample.data.source.local.model.UserEntity
import com.lxquyen.sample.data.source.remote.model.UserDto
import com.lxquyen.sample.domain.model.User
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {
    @Binds
    abstract fun provideUserDto(userDtoMapper: UserDtoMapper): Mapper<UserDto, User>

    @Binds
    abstract fun provideUserEntity(userEntityMapper: UserEntityMapper): Mapper<UserEntity, User>
}