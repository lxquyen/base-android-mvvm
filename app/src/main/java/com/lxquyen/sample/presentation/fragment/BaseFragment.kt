package com.lxquyen.sample.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.lxquyen.sample.common.MyApplication
import com.lxquyen.sample.common.di.component.AppComponent
import com.lxquyen.sample.presentation.view.BaseView

abstract class BaseFragment: Fragment(), BaseView {
    lateinit var unBinder: Unbinder

    abstract fun inject(appComponent: AppComponent)
    abstract fun getLayoutRes(): Int
    abstract fun initView()
    abstract fun initData()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject(MyApplication.instance.appComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutRes(), container, false)
        unBinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroyView() {
        unBinder.unbind()
        super.onDestroyView()
    }

    override fun showProgressDialog(isShow: Boolean) {

    }

}