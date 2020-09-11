package com.harvey.mvvmsample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.harvey.mvvmsample.base.BaseViewModel

/**
 * Created by Harvey on 2020/9/11
 */
class MainViewModel(application: Application) : BaseViewModel(application) {
    val output = ObservableField<String>()
    val input = ObservableField<String>()

    fun request() {
        output.set(input.get())
//        showLoading("111")
//        finish()
    }

    override fun onCreate() {
        output.set("222")
    }
}