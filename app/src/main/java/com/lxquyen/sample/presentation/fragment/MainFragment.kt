package com.lxquyen.sample.presentation.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import butterknife.BindView
import com.lxquyen.sample.R
import com.lxquyen.sample.common.di.component.AppComponent
import com.lxquyen.sample.common.extensions.observeX
import com.lxquyen.sample.common.extensions.setupLoadEndless
import com.lxquyen.sample.domain.model.Response
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.presentation.activity.MainActivity
import com.lxquyen.sample.presentation.adapter.MainAdapter
import com.lxquyen.sample.presentation.adapter.OnButtonClickCallback
import com.lxquyen.sample.presentation.viewmodel.MainViewModel
import com.lxquyen.sample.presentation.widget.EndlessRecyclerViewScrollListener

class MainFragment : BaseFragment() {

    lateinit var mainViewModel: MainViewModel

    @BindView(R.id.swipe_refresh)
    lateinit var swipeRefresh: SwipeRefreshLayout

    @BindView(R.id.rv_user)
    lateinit var rvUser: RecyclerView

    private lateinit var mainAdapter: MainAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add -> {
                val user = User(
                    email = "lxquyen@gmail.com",
                    fullName = "Luu Xuan Quyen",
                    username = "lxquyen",
                    password = "123456"
                )
                mainViewModel.create(user)
            }
        }
        return true
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        swipeRefresh.setOnRefreshListener {
            scrollListener.resetState()
            mainViewModel.read(1)
        }

        mainAdapter = MainAdapter(object : OnButtonClickCallback {
            override fun onBtnEditClicked(position: Int, user: User?) {
                mainViewModel.itemSelected.value = Pair(position,user)
                (activity as MainActivity).addFragment(EditUserFragment(),true)
            }

            override fun onBtnDeleteClicked(user: User?) {
                mainViewModel.delete(user)
            }

        })

        rvUser.apply {
            adapter = mainAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            scrollListener = setupLoadEndless(1) { page, _ ->
                mainViewModel.read(page)
            }
        }
    }

    override fun initViewModel() {
        mainViewModel = ViewModelProviders.of(activity as MainActivity, viewModelFactory).get(MainViewModel::class.java)
        mainViewModel.read(1)
    }

    override fun observable() {
        mainViewModel.response.observeX(this, this::handleResponse)
        mainViewModel.error.observeX(this, this::handleError)
        mainViewModel.status.observeX(this) { status ->
            handleStatusRequest(status, swipeRefresh)
        }
    }

    private fun handleResponse(response: Response<List<User>>?) {
        when (response?.type) {
            Response.NEW -> {
                mainAdapter.addAll(response.model)
            }

            Response.MORE -> {
                mainAdapter.addAll(response.model, true)
            }
            Response.CHANGED -> {
                mainAdapter.update(response.model)
            }

            Response.INSERT -> {
                mainAdapter.insert(response.model)
            }

            Response.REMOVE -> {
                mainAdapter.remove(response.model)
            }
        }
    }

}