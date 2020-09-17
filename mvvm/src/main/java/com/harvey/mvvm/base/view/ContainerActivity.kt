package com.harvey.mvvm.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.harvey.mvvm.R

/**
 *
 * Created by Harvey on 2020/9/14
 */
class ContainerActivity : AppCompatActivity() {
    val FRAGMENT = "FRAGMENT"
    val BUNDLE = "BUNDLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val fragment = initFromIntent(intent)
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.content, fragment!!)
        trans.commitAllowingStateLoss()
    }

    private fun initFromIntent(data: Intent?): Fragment? {
        if (data == null) {
            throw RuntimeException(
                "you must provide a page info to display"
            )
        }
        try {
            val fragmentName = data.getStringExtra(FRAGMENT)
            require(!(fragmentName == null || "" == fragmentName)) { "can not find page fragmentName" }
            val fragmentClass = Class.forName(fragmentName)
            val fragment = fragmentClass.newInstance() as Fragment
            val args = data.getBundleExtra(BUNDLE)
            if (args != null) {
                fragment.arguments = args
            }
            return fragment
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        throw RuntimeException("fragment initialization failed!")
    }
}