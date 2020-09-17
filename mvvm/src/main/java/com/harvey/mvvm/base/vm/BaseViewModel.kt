package com.harvey.mvvm.base.vm

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.*

/**
 * Created by Harvey on 2020/9/11
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    private val observableEvents = MutableLiveData<ViewModelEvent>()

    fun getViewModelEvents(): LiveData<ViewModelEvent> = observableEvents

    /**
     * 触发activity事件
     */
    private fun postViewModelEvent(event: ViewModelEvent) {
        observableEvents.value = event
    }

    /**
     * activity finish
     */
    fun finish() {
        val viewModelEvent = ViewModelEvent().apply {
            event = FINISH_ACTIVITY
        }
        postViewModelEvent(viewModelEvent)
    }

    /**
     * activity 跳转
     */
    fun startActivity(clz: Class<*>) {
        startActivity(clz, null)
    }

    /**
     * activity 跳转
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val viewModelEvent = ViewModelEvent().apply {
            event = START_ACTIVITY
            activity = clz
            this.bundle = bundle
        }
        postViewModelEvent(viewModelEvent)
    }

    /**
     * container activity 跳转
     */
    fun startContainerActivity(fragmentName: String?) {
        startContainerActivity(fragmentName, null)
    }

    /**
     * container activity 跳转
     */
    fun startContainerActivity(fragmentName: String?, bundle: Bundle?) {
        val viewModelEvent = ViewModelEvent().apply {
            event = START_CONTAINER_ACTIVITY
            this.fragmentName = fragmentName
            this.bundle = bundle
        }
        postViewModelEvent(viewModelEvent)
    }

    /**
     * 显示加载框
     */
    fun showLoading(tittle: String) {
        val viewModelEvent = ViewModelEvent().apply {
            event = SHOW_LOADING_DIALOG
            this.tittle = tittle
        }
        postViewModelEvent(viewModelEvent)
    }

    /**
     * 隐藏加载框
     */
    fun dismissDialog() {
        val viewModelEvent = ViewModelEvent().apply {
            event = DISMISS_LOADING_DIALOG
        }
        postViewModelEvent(viewModelEvent)
    }

    //----------------------------- 生命周期 ------------------------------
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }
    //----------------------------- 生命周期 ------------------------------
}