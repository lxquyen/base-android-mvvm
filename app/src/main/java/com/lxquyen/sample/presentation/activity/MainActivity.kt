package com.lxquyen.sample.presentation.activity

import com.lxquyen.sample.presentation.fragment.BaseFragment
import com.lxquyen.sample.presentation.fragment.MainFragment

class MainActivity : BaseActivity() {

    override fun initFragment(): BaseFragment {
        return MainFragment()
    }

    override fun getIdContainer(): Int {
        return android.R.id.content
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
