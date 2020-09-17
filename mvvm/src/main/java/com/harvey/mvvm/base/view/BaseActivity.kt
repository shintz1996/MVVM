package com.harvey.mvvm.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harvey.mvvm.base.vm.AndroidViewModelFactory
import com.harvey.mvvm.base.vm.BaseViewModel
import com.harvey.mvvm.base.vm.ViewModelEvent
import com.hb.dialog.dialog.LoadingDialog
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Harvey on 2020/9/11
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseView {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId = 0

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding(savedInstanceState)
        handleEvent()
        initData()
    }

    /**
     * 处理事件
     */
    private fun handleEvent() {
        viewModel.getViewModelEvents().observe(this, Observer {
            when (it.event) {
                it.START_ACTIVITY -> startActivity(it) //Activity 跳转
                it.FINISH_ACTIVITY -> finish() //Activity 结束
                it.SHOW_LOADING_DIALOG -> showLoading(it) //显示loading框
                it.DISMISS_LOADING_DIALOG -> loadingDialog.hide() //隐藏loading框
                it.START_CONTAINER_ACTIVITY -> startContainerActivity(it) //ContainerActivity 跳转
            }
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

    private fun startContainerActivity(viewModelEvent: ViewModelEvent) {
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra("FRAGMENT", viewModelEvent.fragmentName)
        if (viewModelEvent.bundle != null) {
            intent.putExtras(viewModelEvent.bundle!!)
        }
        startActivity(intent)
    }

    private fun showLoading(viewModelEvent: ViewModelEvent) {
        loadingDialog.apply {
            setCancelable(false)
            setMessage(viewModelEvent.tittle)
            show()
        }
    }

    private fun startActivity(viewModelEvent: ViewModelEvent) {
        val intent = Intent(this, viewModelEvent.activity)
        viewModelEvent.bundle?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    /**
     * 初始化数据
     */
    override fun initData() {
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