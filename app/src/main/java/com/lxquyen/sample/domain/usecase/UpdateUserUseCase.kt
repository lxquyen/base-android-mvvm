package com.lxquyen.sample.domain.usecase

import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.NullPointerException
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor() {
    @Inject
    lateinit var userRepos: UserRepos

    fun execute(user: User?): Observable<User> {
        return user?.let {
            userRepos.updateUser(user)
        } ?: Observable.error(NullPointerException())
    }
}