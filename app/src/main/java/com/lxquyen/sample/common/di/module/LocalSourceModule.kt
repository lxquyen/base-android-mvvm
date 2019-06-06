package com.lxquyen.sample.common.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.lxquyen.sample.data.source.local.DatabaseManager
import com.lxquyen.sample.data.source.local.dao.UserDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalSourceModule {

    @Provides
    @Singleton
    fun provideUserDAO(databaseManager: DatabaseManager): UserDAO{
        return databaseManager.userDAO()
    }


    @Provides
    @Singleton
    fun provideDatabaseManager(context: Context): DatabaseManager {
        return Room.databaseBuilder(context, DatabaseManager::class.java, DatabaseManager.DATABASE_NAME)
            .build()
    }
}