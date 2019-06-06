package com.lxquyen.sample.data.repository

import com.lxquyen.sample.common.extensions.observerOnDatabase
import com.lxquyen.sample.data.mapper.base.Mapper
import com.lxquyen.sample.data.source.local.dao.UserDAO
import com.lxquyen.sample.data.source.local.model.UserEntity
import com.lxquyen.sample.data.source.remote.UserRemoteSource
import com.lxquyen.sample.data.source.remote.model.UserDto
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class UserReposImpl @Inject constructor() : UserRepos {

    @Inject
    lateinit var userRemoteSource: UserRemoteSource

    @Inject
    lateinit var userDAO: UserDAO

    @Inject
    lateinit var mapper: Mapper<UserDto, User>

    @Inject
    lateinit var mapperEntity: Mapper<UserEntity, User>


    override fun createUser(user: User): Observable<User> {
        return userRemoteSource
            .createUser(mapper.reverse(user))
            .map { mapper.map(it) }
            .observerOnDatabase {
                userDAO.create(mapperEntity.reverse(it))
            }
    }

    override fun getUsers(page: Int, limit: Int): Observable<List<User>> {
        return when (page) {
            1 -> {
                Observable.merge(
                    getUsersLocal(),
                    getUsersRemote(page, limit)
                )
            }
            else -> {
                userRemoteSource
                    .readUsers(page, limit)
                    .map {
                        mapper.map(it)
                    }
            }
        }
    }

    override fun updateUser(user: User): Observable<User> {
        return userRemoteSource
            .updateUser(user.id, mapper.reverse(user))
            .map { mapper.map(it) }
            .observerOnDatabase {
                userDAO.update(mapperEntity.reverse(it))
            }
    }

    override fun deleteUser(user: User): Observable<User> {
        return userRemoteSource
            .deleteUser(user.id)
            .map { mapper.map(it) }
            .observerOnDatabase {
                userDAO.delete(mapperEntity.reverse(it))
            }
    }

    private fun getUsersRemote(page: Int, limit: Int): Observable<List<User>> {
        return userRemoteSource
            .readUsers(page, limit)
            .map {
                mapper.map(it)
            }
            .observerOnDatabase {
                if (page == 1) {
                    userDAO.clear()
                }
                userDAO.create(mapperEntity.reverse(it))
            }
    }

    private fun getUsersLocal(): Observable<List<User>> {
        return userDAO
            .getAllUsers()
            .toObservable()
            .map {
                mapperEntity.map(it)
            }
    }

}