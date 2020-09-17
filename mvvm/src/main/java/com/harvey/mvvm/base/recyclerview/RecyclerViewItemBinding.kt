package com.harvey.mvvm.base.recyclerview

import androidx.databinding.ViewDataBinding

/**
 *
 * Created by Harvey on 2020/9/17
 */
abstract class RecyclerViewItemBinding(val layoutId: Int, val variableId: Int) {
    abstract fun onBind(dataBinding: ViewDataBinding, position: Int, any: Any)
}