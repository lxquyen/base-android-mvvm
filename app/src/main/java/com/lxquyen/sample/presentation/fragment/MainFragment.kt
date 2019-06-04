package com.lxquyen.sample.presentation.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.presentation.activity.MainActivity
import com.lxquyen.sample.presentation.adapter.MainAdapter
import com.lxquyen.sample.presentation.adapter.OnButtonClickCallback
import com.lxquyen.sample.presentation.view.MainView
import com.lxquyen.sample.presentation.viewmodel.UserViewModel
import com.lxquyen.sample.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : BaseFragment(), MainView {
    companion object {
        const val RQ_EDIT = 99
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: UserViewModel


    @BindView(R.id.swipe_refresh)
    lateinit var swipeRefresh: SwipeRefreshLayout

    @BindView(R.id.rv_user)
    lateinit var rvUser: RecyclerView

    lateinit var mainAdapter: MainAdapter


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

                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)
                val user = User(
                    email = "lxquyen@gmail.com",
                    fullName = "Luu Xuan Quyen",
                    username = "lxquyen",
                    password = "123456"
                )
            }
        }
        return true
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        swipeRefresh.setOnRefreshListener {
        }

        mainAdapter = MainAdapter(object : OnButtonClickCallback {
            override fun onBtnEditClicked(position: Int, user: User?) {

            }

            override fun onBtnDeleteClicked(id: String?) {
            }

        })

        rvUser.apply {
            adapter = mainAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun initData() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(UserViewModel::class.java)
        viewModel.read()
        viewModel.users.observe(this, Observer { users ->
            mainAdapter.users = users?.toMutableList() ?: mutableListOf()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RQ_EDIT -> {
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

}