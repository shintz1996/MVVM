package com.harvey.mvvmsample.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

/**
 * Created by Harvey on 2020/9/11
 */
class AndroidViewModelFactory(application: Application) : NewInstanceFactory() {
    private val mApplication: Application = application

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                modelClass.getConstructor(Application::class.java).newInstance(mApplication)
            } catch (e: Exception) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        } else super.create(modelClass)
    }

    companion object {
        private var sInstance: AndroidViewModelFactory? = null
        fun getInstance(application: Application): AndroidViewModelFactory {
            if (sInstance == null) {
                sInstance = AndroidViewModelFactory(application)
            }
            return sInstance as AndroidViewModelFactory
        }
    }

}