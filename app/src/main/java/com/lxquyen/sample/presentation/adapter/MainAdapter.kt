package com.lxquyen.sample.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lxquyen.sample.R
import com.lxquyen.sample.common.extensions.inflateView
import com.lxquyen.sample.common.extensions.loadImage
import com.lxquyen.sample.domain.model.User

interface OnButtonClickCallback {
    fun onBtnEditClicked(position: Int, user: User?)
    fun onBtnDeleteClicked(id: String?)
}

class MainAdapter(private val onButtonClickCallback: OnButtonClickCallback) :
    RecyclerView.Adapter<MainAdapter.UserViewHolder>() {
    var users = mutableListOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainAdapter.UserViewHolder {
        val view = p0.inflateView(R.layout.item_user)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        payloads.forEach {
            when(it){
                is User ->{
                    holder.bindText(it)
                }
            }
        }
    }

    override fun onBindViewHolder(p0: MainAdapter.UserViewHolder, p1: Int) {
        p0.bindData(users[p1])
    }

    fun update(positionSelected: Int, user: User?) {
        user?.let {
            users[positionSelected].apply {
                this.username = it.username
                this.password = it.password
                this.email = it.email
                this.fullName = it.fullName
            }
            notifyItemChanged(positionSelected,user)
        }
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.img_avatar)
        lateinit var imgAvatar: ImageView

        @BindView(R.id.tv_info)
        lateinit var tvInfo: TextView

        var user: User? = null

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bindData(user: User) {
            this.user = user
            imgAvatar.loadImage(user.avatar)
            tvInfo.text = user.toString()
        }

        @OnClick(R.id.btn_edit, R.id.btn_delete)
        fun onBtnClicked(view: View) {
            when (view.id) {
                R.id.btn_edit -> onButtonClickCallback.onBtnEditClicked(adapterPosition, user)
                R.id.btn_delete -> {
                    users.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                    onButtonClickCallback.onBtnDeleteClicked(user?.id)
                }
            }
        }

        fun bindText(it: User) {
            this.user = it
            tvInfo.text = user.toString()
        }
    }

}