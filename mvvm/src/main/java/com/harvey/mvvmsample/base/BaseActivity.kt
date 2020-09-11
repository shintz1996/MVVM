package com.harvey.mvvmsample.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Harvey on 2020/9/11
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding(savedInstanceState)
        handleEvent()
    }

    /**
     * 处理activity事件
     */
    private fun handleEvent() {
        viewModel.getViewModelEvents().observe(this, Observer {
            Log.e("handle","handle")
            val event = it.takeUnless { it == null || it.handled } ?: return@Observer
            event.handle(this);
        })
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutRes(savedInstanceState))
        viewModelId = getVariableId()

        val modelClass: Class<BaseViewModel>
        val type: Type = javaClass.genericSuperclass!!
        modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<BaseViewModel>
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            BaseViewModel::class.java
        }

        val factory = AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory).get(modelClass) as VM
//        viewModel = ViewModelProvider(this).get(modelClass) as VM

        lifecycle.addObserver(viewModel)
        binding.setVariable(viewModelId, viewModel)
        binding.lifecycleOwner = this
    }

    /**
     * 获取ViewModel id
     */
    abstract fun getVariableId(): Int

    /**
     * 获取页面布局res
     */
    abstract fun getLayoutRes(savedInstanceState: Bundle?): Int
}