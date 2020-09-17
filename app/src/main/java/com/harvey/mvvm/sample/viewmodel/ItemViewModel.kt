package com.harvey.mvvm.sample.viewmodel

import androidx.databinding.ObservableField

/**
 *
 * Created by Harvey on 2020/9/15
 */
class ItemViewModel(listViewModel: ListViewModel, item: Item) {
    var item = ObservableField<Item>()

    init {
        this.item.set(item)
    }
}

data class Item(val name: String, val age: Int)