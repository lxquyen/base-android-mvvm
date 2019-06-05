package com.lxquyen.sample.domain.usecase

import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CreateUserUseCase @Inject constructor() {
    @Inject
    lateinit var userRepos: UserRepos

    fun execute(user: User): Observable<User>{
        return userRepos.createUser(user)
    }
}