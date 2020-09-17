package com.harvey.mvvm.base.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * Created by Harvey on 2020/9/16
 */
object RecyclerViewBindingAdapter {
    @BindingAdapter(value = ["itemBinding", "items"], requireAll = false)
    @JvmStatic
    fun setAdapter(recyclerView: RecyclerView, itemBinding: RecyclerViewItemBinding?, items: List<*>) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = RecyclerViewAdapter().apply {
            recyclerViewItemBinding = itemBinding!!
            list = items
        }
    }
}