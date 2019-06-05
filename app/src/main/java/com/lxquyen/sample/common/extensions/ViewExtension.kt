package com.lxquyen.sample.common.extensions

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lxquyen.sample.presentation.widget.EndlessRecyclerViewScrollListener

fun ViewGroup.inflateView(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun RecyclerView.setupLoadEndless(currentPage: Int, block: (Int, Int) -> Unit): EndlessRecyclerViewScrollListener {
    val onScrollListener =
        object : EndlessRecyclerViewScrollListener(currentPage, layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                block(page, totalItemsCount)
            }

        }
    addOnScrollListener(onScrollListener)
    return onScrollListener
}

fun TextView.data(): String {
    return this.text.toString()
}

