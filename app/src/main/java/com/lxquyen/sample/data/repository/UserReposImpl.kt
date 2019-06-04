package com.lxquyen.sample.data.repository

import com.lxquyen.sample.data.mapper.base.Mapper
import com.lxquyen.sample.data.source.remote.UserRemoteSource
import com.lxquyen.sample.data.source.remote.model.UserDto
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserReposImpl @Inject constructor() : UserRepos {

    @Inject
    lateinit var userRemoteSource: UserRemoteSource

    @Inject
    lateinit var mapper: Mapper<UserDto, User>


    override fun createUser(user: User): Completable {
        return userRemoteSource.createUser(mapper.reverse(user))
    }

    override fun getUsers(): Observable<List<User>> {
        return userRemoteSource
            .readUsers()
            .map {
                mapper.map(it)
            }
    }

    override fun updateUser(user: User): Observable<User> {
        return userRemoteSource
            .updateUser(user.id, mapper.reverse(user))
            .map { mapper.map(it) }
    }

    override fun deleteUser(id: String?): Completable {
        return userRemoteSource
            .deleteUser(id)
    }

}