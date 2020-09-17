package com.harvey.mvvm.sample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.harvey.mvvm.BR
import com.harvey.mvvm.R
import com.harvey.mvvm.base.view.BaseFragment
import com.harvey.mvvm.databinding.FragmentTestBinding
import com.harvey.mvvm.sample.viewmodel.TestViewModel

/**
 *
 * Created by Harvey on 2020/9/14
 */
class TestFragment : BaseFragment<FragmentTestBinding, TestViewModel>() {
    override fun getVariableId(): Int {
        return BR.viewModel
    }

    override fun getResLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test
    }
}