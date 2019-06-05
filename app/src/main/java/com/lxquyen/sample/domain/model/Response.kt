package com.lxquyen.sample.domain.model

import android.support.annotation.IntDef


@IntDef(Response.NEW, Response.INSERT, Response.CHANGED, Response.REMOVE)
annotation class TypeResponse

class Response<T>(@TypeResponse val type: Int, val model: T) {
    companion object {
        const val NEW = 0
        const val INSERT = 1
        const val CHANGED = 2
        const val REMOVE = 3
        const val MORE = 4
    }
}
