package com.lxquyen.sample.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lxquyen.sample.data.source.local.dao.UserDAO
import com.lxquyen.sample.data.source.local.model.UserEntity

@Database(entities = [UserEntity::class],version = 1,exportSchema = false)
abstract class DatabaseManager: RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "room_db"
    }

    abstract fun userDAO(): UserDAO

}