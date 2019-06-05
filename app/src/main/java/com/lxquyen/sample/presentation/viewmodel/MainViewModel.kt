package com.lxquyen.sample.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.lxquyen.sample.common.extensions.addToCompositedisposable
import com.lxquyen.sample.common.extensions.completableTransformer
import com.lxquyen.sample.common.extensions.observableTransformer
import com.lxquyen.sample.domain.model.Response
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.domain.usecase.CreateUserUseCase
import com.lxquyen.sample.domain.usecase.DeleteUserUseCase
import com.lxquyen.sample.domain.usecase.ReadUsersUseCase
import com.lxquyen.sample.domain.usecase.UpdateUserUseCase
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var readUsersUC: ReadUsersUseCase

    @Inject
    lateinit var createUserUC: CreateUserUseCase

    @Inject
    lateinit var deleteUserUC: DeleteUserUseCase

    @Inject
    lateinit var updateUserUC: UpdateUserUseCase

    val response = MutableLiveData<Response<List<User>>>()

    val itemSelected = MutableLiveData<Pair<Int, User?>>()

    init {
        Timber.e("init")
    }

    fun read(page: Int, limit: Int = 10) {
        readUsersUC
            .execute(page, limit)
            .compose(observableTransformer(status))
            .subscribe(
                {
                    val type = if (page == 1) {
                        Response.NEW
                    } else {
                        Response.MORE
                    }
                    response.value = Response(type, it)
                }, this::handleError
            )
            .addToCompositedisposable(disposables)
    }

    fun create(user: User) {
        createUserUC
            .execute(user)
            .compose(observableTransformer(status))
            .subscribe(
                {
                    response.value = Response(Response.INSERT, listOf(it))
                }, this::handleError
            )
            .addToCompositedisposable(disposables)
    }

    fun update(user: User?) {
        updateUserUC
            .execute(user)
            .compose(observableTransformer(status))
            .subscribe(
                {
                    response.value = Response(Response.CHANGED, listOf(it))
                }, this::handleError
            )
            .addToCompositedisposable(disposables)

    }

    fun delete(id: String?) {
        deleteUserUC
            .execute(id)
            .compose(completableTransformer(status))
            .subscribe(
                {
                    val user = User(id = id)
                    response.value = Response(Response.REMOVE, listOf(user))
                }, this::handleError
            )
            .addToCompositedisposable(disposables)

    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("here")
    }

    private fun handleError(throwable: Throwable) {
        error.value = throwable
    }
}