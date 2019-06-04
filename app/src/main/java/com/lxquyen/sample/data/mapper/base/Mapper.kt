package com.lxquyen.sample.data.mapper.base

abstract class Mapper<From, To> {
    abstract fun map(from: From): To
    abstract fun reverse(to: To): From

    fun map(list: List<From>?): List<To> {
        val result = mutableListOf<To>()

        list?.mapTo(result) {
            return@mapTo map(it)
        }

        return result
    }

    fun reverse(list: List<To>?): List<From> {
        val result = mutableListOf<From>()

        list?.mapTo(result) {
            return@mapTo reverse(it)
        }

        return result
    }

}