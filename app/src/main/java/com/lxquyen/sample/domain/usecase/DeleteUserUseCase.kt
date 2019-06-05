package com.lxquyen.sample.domain.usecase

import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.Completable
import java.lang.NullPointerException
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor() {
    @Inject
    lateinit var userRepos: UserRepos

    fun execute(id: String?): Completable {
        return id?.let {
            return userRepos.deleteUser(id)
        } ?: run {
            return@run Completable.error(NullPointerException())
        }
    }
}