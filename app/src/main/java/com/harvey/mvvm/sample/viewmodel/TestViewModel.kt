package com.harvey.mvvm.sample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.harvey.mvvm.base.vm.BaseViewModel
import com.harvey.mvvm.sample.view.ListActivity

/**
 *
 * Created by Harvey on 2020/9/14
 */
class TestViewModel(application: Application) : BaseViewModel(application) {
    val output = ObservableField<String>()
    val input = ObservableField<String>()

    fun request() {
        startActivity(ListActivity::class.java)
    }

    override fun onCreate() {
        output.set("4654")
    }
}