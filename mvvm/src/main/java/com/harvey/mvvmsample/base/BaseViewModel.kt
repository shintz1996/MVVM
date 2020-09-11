package com.harvey.mvvmsample.base

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.hb.dialog.dialog.LoadingDialog

/**
 * Created by Harvey on 2020/9/11
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    private val observableEvents = MutableLiveData<ViewModelEvent>()
    private var loadingDialog: LoadingDialog? = null

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
        postViewModelEvent(object : ViewModelEvent() {
            override fun handle(activity: AppCompatActivity) {
                activity.finish()
            }
        })
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
        postViewModelEvent(object : ViewModelEvent() {
            override fun handle(activity: AppCompatActivity) {
                val intent = Intent(activity, clz)
                if (bundle != null) {
                    intent.putExtras(bundle)
                }
                activity.startActivity(intent)
            }
        })
    }

    /**
     * 显示加载框
     */
    fun showLoading(tittle: String) {
        postViewModelEvent(object : ViewModelEvent() {
            override fun handle(activity: AppCompatActivity) {
                if (loadingDialog == null) {
                    loadingDialog = LoadingDialog(activity)
                    loadingDialog?.setCancelable(false)
                    loadingDialog?.setMessage(tittle)
                }
                loadingDialog?.show()
            }
        })
    }

    /**
     * 隐藏加载框
     */
    fun dismissDialog() {
        postViewModelEvent(object : ViewModelEvent() {
            override fun handle(activity: AppCompatActivity) {
                if (loadingDialog != null) {
                    loadingDialog?.hide()
                }
            }
        })
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

/**
 * activity事件
 */
abstract class ViewModelEvent {
    var event = 0;
    var handled: Boolean = false
        private set

    open fun handle(activity: AppCompatActivity) {
        handled = true
    }
}