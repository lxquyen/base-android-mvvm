package com.lxquyen.sample.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.lxquyen.sample.common.MyApplication
import com.lxquyen.sample.common.di.component.AppComponent
import com.lxquyen.sample.presentation.view.BaseView
import com.lxquyen.sample.presentation.viewmodel.BaseViewModel
import com.lxquyen.sample.presentation.viewmodel.Status
import com.lxquyen.sample.presentation.viewmodel.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment : Fragment(), BaseView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var unBinder: Unbinder

    abstract fun inject(appComponent: AppComponent)
    abstract fun getLayoutRes(): Int
    abstract fun initView()
    abstract fun initViewModel()
    abstract fun observable()


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
        initViewModel()
        observable()
    }

    override fun onDestroyView() {
        unBinder.unbind()
        super.onDestroyView()
    }

    override fun showProgressDialog(isShow: Boolean) {

    }

    protected fun handleStatusRequest(@Status status: Int?, swipeRefreshLayout: SwipeRefreshLayout? = null) {
        when (status) {
            BaseViewModel.RQ_START -> {
                showProgressDialog(true)
            }
            BaseViewModel.RQ_FINISH -> {
                swipeRefreshLayout?.isRefreshing = false
                showProgressDialog(false)
            }
        }
    }

    protected fun handleError(throwable: Throwable?){
        Timber.e(throwable)
    }

}