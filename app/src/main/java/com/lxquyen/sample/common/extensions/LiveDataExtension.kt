package com.lxquyen.sample.common.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.observeX(owner: LifecycleOwner, block: (T?) -> Unit) {
    this.observe(owner, Observer {
        block(it)
    })
}