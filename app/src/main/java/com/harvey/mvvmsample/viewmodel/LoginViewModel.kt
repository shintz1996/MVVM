package com.harvey.mvvmsample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.harvey.mvvmsample.base.BaseViewModel
import com.harvey.mvvmsample.view.MainActivity
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
            showLoading("loading")
            sortList()
            // Modify UI
            dismissDialog()
            startActivity(MainActivity::class.java)
        }
    }

    private suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
        delay(2000)
    }
}