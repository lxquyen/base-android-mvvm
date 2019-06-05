package com.lxquyen.sample.domain.usecase

import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.Observable
import javax.inject.Inject

class ReadUsersUseCase @Inject constructor() {
    @Inject
    lateinit var repos: UserRepos

    fun execute(page: Int, limit: Int) : Observable<List<User>> {
        return repos.getUsers(page,limit)
    }
}