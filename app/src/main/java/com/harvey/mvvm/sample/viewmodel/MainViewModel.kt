package com.harvey.mvvm.sample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import com.harvey.mvvm.base.vm.BaseViewModel
import com.harvey.mvvm.sample.view.TestFragment

/**
 * Created by Harvey on 2020/9/11
 */
class MainViewModel(application: Application) : BaseViewModel(application) {
    val output = ObservableField<String>()
    val input = ObservableField<String>()

    fun request() {
        output.set(input.get())
        startContainerActivity(TestFragment::class.java.canonicalName)
    }

    override fun onCreate() {
        output.set("222")
    }
}