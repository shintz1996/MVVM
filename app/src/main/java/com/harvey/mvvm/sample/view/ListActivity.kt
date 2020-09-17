package com.harvey.mvvm.sample.view

import android.os.Bundle
import com.harvey.mvvm.BR
import com.harvey.mvvm.R
import com.harvey.mvvm.base.view.BaseActivity
import com.harvey.mvvm.databinding.ActivityListBinding
import com.harvey.mvvm.sample.viewmodel.ListViewModel

/**
 *
 * Created by Harvey on 2020/9/15
 */
class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>() {
    override fun getVariableId(): Int {
        return BR.viewModel
    }

    override fun getLayoutRes(savedInstanceState: Bundle?): Int {
        return R.layout.activity_list
    }
}