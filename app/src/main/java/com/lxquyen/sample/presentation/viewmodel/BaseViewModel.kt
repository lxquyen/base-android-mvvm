package com.lxquyen.sample.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.IntDef
import io.reactivex.disposables.CompositeDisposable

@IntDef(
    BaseViewModel.RQ_START,
    BaseViewModel.RQ_FINISH
)
annotation class Status

abstract class BaseViewModel : ViewModel() {
    companion object {
        const val RQ_START = 0x00
        const val RQ_FINISH = 0x01
    }

    protected val disposables: CompositeDisposable = CompositeDisposable()

    val status = MutableLiveData<@Status Int>()

    val error = MutableLiveData<Throwable>()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}