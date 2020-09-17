package com.harvey.mvvm.base.vm

import android.os.Bundle

/**
 *
 * Created by Harvey on 2020/9/14
 */
class ViewModelEvent {
    val START_ACTIVITY = 0
    val FINISH_ACTIVITY = 1
    val SHOW_LOADING_DIALOG = 2
    val DISMISS_LOADING_DIALOG = 3
    val START_CONTAINER_ACTIVITY = 4

    var bundle: Bundle? = null
    var tittle: String? = null
    var activity: Class<*>? = null
    var event: Int = -1
    var fragmentName: String? = null
}