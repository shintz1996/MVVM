package com.harvey.mvvmsample.view

import android.os.Bundle
import com.harvey.mvvmsample.BR
import com.harvey.mvvmsample.R
import com.harvey.mvvmsample.base.BaseActivity
import com.harvey.mvvmsample.databinding.ActivityLoginBinding
import com.harvey.mvvmsample.viewmodel.LoginViewModel

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
