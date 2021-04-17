package com.fongfuse.githubdemo.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setScrollListenerLoadMore(layoutManager: LinearLayoutManager,
                                           callback: (totalItemCount: Int, lastVisibleItem: Int) -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView,
                                dx: Int,
                                dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            callback.invoke(totalItemCount, lastVisibleItem)
        }
    })
}