package com.lxquyen.sample.domain.repository

import com.lxquyen.sample.domain.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepos {
    fun createUser(user: User): Completable

    fun getUsers(): Observable<List<User>>

    fun updateUser(user: User): Observable<User>

    fun deleteUser(id: String?): Completable
}