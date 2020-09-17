package com.harvey.mvvm.sample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.harvey.mvvm.base.vm.BaseViewModel
import com.harvey.mvvm.sample.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Created by Harvey on 2020/9/11
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {
    val username = ObservableField<String>()
    val password = ObservableField<String>()

    fun login() {
        viewModelScope.launch {
//            showLoading("loading")
//            sortList()
//            // Modify UI
//            dismissDialog()
            startActivity(MainActivity::class.java)
        }
    }

    private suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
        delay(2000)
    }
}