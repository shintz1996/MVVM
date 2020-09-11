package com.harvey.mvvmsample.view

import android.os.Bundle
import com.harvey.mvvmsample.BR
import com.harvey.mvvmsample.viewmodel.MainViewModel
import com.harvey.mvvmsample.R
import com.harvey.mvvmsample.base.BaseActivity
import com.harvey.mvvmsample.databinding.ActivityMainBinding

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
