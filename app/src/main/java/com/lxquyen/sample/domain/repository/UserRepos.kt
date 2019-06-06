package com.lxquyen.sample.domain.repository

import com.lxquyen.sample.domain.model.User
import io.reactivex.Observable

interface UserRepos {
    fun createUser(user: User): Observable<User>

    fun getUsers(page: Int, limit: Int): Observable<List<User>>

    fun updateUser(user: User): Observable<User>

    fun deleteUser(user: User): Observable<User>
}