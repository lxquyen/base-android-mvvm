package com.lxquyen.sample.presentation.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.lxquyen.sample.R
import com.lxquyen.sample.common.di.component.AppComponent
import com.lxquyen.sample.common.extensions.*
import com.lxquyen.sample.domain.model.Response
import com.lxquyen.sample.domain.model.User
import com.lxquyen.sample.presentation.activity.MainActivity
import com.lxquyen.sample.presentation.viewmodel.MainViewModel
import timber.log.Timber
import javax.inject.Inject

class EditUserFragment : BaseFragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @BindView(R.id.tv_id)
    lateinit var tvId: TextView

    @BindView(R.id.edit_username)
    lateinit var editUsername: EditText

    @BindView(R.id.edit_password)
    lateinit var editPassword: EditText

    @BindView(R.id.edit_email)
    lateinit var editEmail: EditText

    @BindView(R.id.edit_fullname)
    lateinit var editFullName: EditText

    @BindView(R.id.img_avatar)
    lateinit var imgAvatar: ImageView

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater?.inflate(R.menu.edit, menu)

        menu?.findItem(R.id.action_save)?.setOnMenuItemClickListener {
            val user = User().apply {
                this.id = tvId.data()
                this.username = editUsername.data()
                this.password = editPassword.data()
                this.email = editEmail.data()
                this.fullName = editFullName.data()
            }

            mainViewModel.update(user)
            return@setOnMenuItemClickListener true
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_edit
    }

    override fun initView() {

    }

    override fun initViewModel() {
        mainViewModel = ViewModelProviders.of(activity as MainActivity, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun observable() {
        mainViewModel.itemSelected.observeX(this, this::handleItemSelected)
        mainViewModel.response.observeX(this, this::handleResponse)
    }

    private fun handleItemSelected(data: Pair<Int, User?>?) {
        data?.second?.let {
            imgAvatar.loadImage(it.avatar)
            tvId.text = it.id
            editUsername.setText(it.username)
            editEmail.setText(it.email)
            editPassword.setText(it.password)
            editFullName.setText(it.fullName)
        }
    }

    private fun handleResponse(response: Response<List<User>>?) {
        if (response?.type == Response.CHANGED) {
            activity?.hideKeyboard()
            activity?.onBackPressed()
        }
    }
}
