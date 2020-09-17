package com.harvey.mvvm.sample.view

import android.os.Bundle
import com.harvey.mvvm.BR
import com.harvey.mvvm.R
import com.harvey.mvvm.base.view.BaseActivity
import com.harvey.mvvm.databinding.ActivityLoginBinding
import com.harvey.mvvm.sample.viewmodel.LoginViewModel

/**
 * Created by Harvey on 2020/9/11
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getLayoutRes(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }

    override fun getVariableId(): Int {
        return BR.viewModel
    }
}
