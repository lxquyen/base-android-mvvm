package com.lxquyen.sample.common.di.module

import com.lxquyen.sample.data.repository.UserReposImpl
import com.lxquyen.sample.domain.repository.UserRepos
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepos(userReposImpl: UserReposImpl): UserRepos {
        return userReposImpl
    }
}