package com.lxquyen.sample.data.source.local.dao

import android.arch.persistence.room.*
import com.lxquyen.sample.data.source.local.model.UserEntity
import io.reactivex.Maybe

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(userEntity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(userEntities: List<UserEntity>)

    @Query("SELECT * FROM UserEntity")
    fun getAllUsers() : Maybe<List<UserEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("DELETE FROM UserEntity")
    fun clear()
}