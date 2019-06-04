package com.lxquyen.sample.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lxquyen.sample.common.extensions.addToCompositedisposable
import com.lxquyen.sample.common.extensions.completableTransformer
import com.lxquyen.sample.common.extensions.observableTransformer
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.repository.UserRepos
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class UserViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repos: UserRepos

    val disposables: CompositeDisposable = CompositeDisposable()

    val users = MutableLiveData<List<User>>()
    val userSelected = MutableLiveData<User>()

    init {
        Timber.e("init")
    }


    fun create(user: User) {
        repos.createUser(user)
            .andThen(repos.getUsers())
            .compose(observableTransformer())
            .subscribe({
                users.value = it
            }, Timber::e)
            .addToCompositedisposable(disposables)

    }

    fun read(){
        repos
            .getUsers()
            .compose(observableTransformer())
            .subscribe({
                users.value = it
            }, Timber::e)
            .addToCompositedisposable(disposables)
    }

    fun update(user: User){
        repos
            .updateUser(user)
            .compose(observableTransformer())
            .subscribe({

            },Timber::e)
            .addToCompositedisposable(disposables)
    }

    fun delete(user: User){
        repos
            .deleteUser(user.id)
            .compose(completableTransformer())
            .subscribe({

            },Timber::e)
            .addToCompositedisposable(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("here")
        disposables.clear()

    }

}