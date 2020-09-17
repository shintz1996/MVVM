package com.harvey.mvvm.sample.view

import android.os.Bundle
import com.harvey.mvvm.BR
import com.harvey.mvvm.sample.viewmodel.MainViewModel
import com.harvey.mvvm.R
import com.harvey.mvvm.base.view.BaseActivity
import com.harvey.mvvm.databinding.ActivityMainBinding

/**
 * Created by Harvey on 2020/9/11
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutRes(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun getVariableId(): Int {
        return BR.viewModel
    }
}
