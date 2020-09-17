package com.harvey.mvvm.base.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    lateinit var recyclerViewItemBinding: RecyclerViewItemBinding
    lateinit var list: List<*>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(recyclerViewItemBinding.layoutId, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.setVariable(recyclerViewItemBinding.variableId, list[position])
        recyclerViewItemBinding.onBind(holder.binding!!, position, list[position]!!)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ViewDataBinding? by lazy {
            DataBindingUtil.bind<ViewDataBinding>(itemView)
        }
    }
}