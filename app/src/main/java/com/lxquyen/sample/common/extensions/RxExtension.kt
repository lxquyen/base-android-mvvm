package com.lxquyen.sample.common.extensions

import com.lxquyen.sample.presentation.view.BaseView
import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.addToCompositedisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> observableTransformer(view: BaseView? = null): ObservableTransformer<T, T> {
    return ObservableTransformer { observable ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showProgressDialog(true) }
            .doFinally { view?.showProgressDialog(false) }
    }
}

fun completableTransformer(view: BaseView? = null): CompletableTransformer {
    return CompletableTransformer { observable ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showProgressDialog(true) }
            .doFinally { view?.showProgressDialog(false) }
    }
}


fun <T> Observable<T>.observerOnDatabase(block: (T) -> Unit): Observable<T> {
    return this.observeOn(Schedulers.computation())
        .doOnNext(block)
        .observeOn(Schedulers.io())
}


fun Completable.observerOnDatabase(block: () -> Unit): Completable {
    return this.observeOn(Schedulers.computation())
        .andThen { block() }
        .observeOn(Schedulers.io())
}