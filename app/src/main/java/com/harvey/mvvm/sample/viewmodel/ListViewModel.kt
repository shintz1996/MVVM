package com.harvey.mvvm.sample.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.harvey.mvvm.BR
import com.harvey.mvvm.R
import com.harvey.mvvm.base.recyclerview.RecyclerViewItemBinding
import com.harvey.mvvm.base.vm.BaseViewModel

/**
 *
 * Created by Harvey on 2020/9/15
 */
class ListViewModel(application: Application) : BaseViewModel(application) {

    val itemList = ObservableField<ArrayList<ItemViewModel>>()

    val itemBinding = object : RecyclerViewItemBinding(R.layout.item, BR.item) {
        override fun onBind(dataBinding: ViewDataBinding, position: Int, any: Any) {
            val item = (any as ItemViewModel).item.get()
            Log.e("", item?.name!!)
        }
    }

    override fun onCreate() {
        val list = ArrayList<ItemViewModel>()
        for (index in 1..100) {
            val itemViewModel = ItemViewModel(this, Item(index.toString(), index))
            list.add(itemViewModel)
        }
        itemList.set(list)
    }
}

